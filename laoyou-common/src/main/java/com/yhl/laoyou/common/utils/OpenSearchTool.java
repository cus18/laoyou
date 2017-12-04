package com.yhl.laoyou.common.utils;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchResult;
import com.aliyun.opensearch.sdk.generated.document.DocumentConstants;
import com.aliyun.opensearch.sdk.generated.search.*;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.aliyun.opensearch.search.SearchParamsBuilder;
import com.google.common.collect.Lists;
import com.yhl.laoyou.common.config.Global;
import com.aliyun.opensearch.sdk.dependencies.org.json.JSONArray;
import com.aliyun.opensearch.sdk.dependencies.org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by sunxiao on 2017/6/20.
 */
public class OpenSearchTool {
    private static OpenSearchClient serviceClient = null;
    private static SearcherClient searcherClient = null;

    static {
        String accesskey = Global.getConfig("aliyun.accesskey");
        String secret =  Global.getConfig("aliyun.secret");
        String host =  Global.getConfig("opensearch.host");

        //创建并构造OpenSearch对象
        OpenSearch openSearch = new OpenSearch(accesskey, secret, host);
        //创建OpenSearchClient对象，并以OpenSearch对象作为构造参数
        serviceClient = new OpenSearchClient(openSearch);
        //创建SearcherClient对象，并以OpenSearchClient对象作为构造参数
        searcherClient = new SearcherClient(serviceClient);
    }

    /**
     *
     * @param fields Lists.newArrayList("id", "name", "tips", "departparentname", "departchildname", "organchildname")
     * @param query
     * @param sort
     * @return
     */
    public static String search(int pageNo, int pageSize, String appName , ArrayList fields, String query, Sort sort) throws Exception{
        JSONObject obj = null;
        try {
            Config config = new Config(Lists.newArrayList(appName));
            if(pageSize != -1){
                config.setStart((pageNo-1)*pageSize);
                config.setHits(pageSize);
            }
            //设置返回格式为json,目前只支持返回xml和json格式，暂  不支持返回fulljson类型
            config.setSearchFormat(SearchFormat.JSON);
            // 设置搜索结果返回应用中哪些字段
            config.setFetchFields(fields);
            // 创建参数对象
            SearchParams searchParams = new SearchParams(config);
            // 设置查询子句，若需多个索引组合查询，需要setQuery处合并，否则若设置多个setQuery后面的会替换前面查询
            //searchParams.setQuery("id:'" + 24770 + "'|'"+24771 +"'|'" +24772 +"'" );
            if(query!=null && !"".equals(query)){
                searchParams.setQuery("default:"+query);
            }

            //Sort sorter = new Sort();
            //sorter.addToSortFields(new SortField("id", Order.DECREASE)); //设置id字段降序
            //sorter.addToSortFields(new SortField("name", Order.INCREASE)); //若id相同则以RANK升序
            //添加Sort对象参数
            searchParams.setSort(sort);

            // SearchParams的工具类，提供了更为便捷的操作
            SearchParamsBuilder paramsBuilder = SearchParamsBuilder.create(searchParams);
            SearchResult searchResult = searcherClient.execute(paramsBuilder);
            String result = searchResult.getResult();
            obj = new JSONObject(result);

            // 输出查询结果
            System.out.println(obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj.getJSONObject("result").toString();
    }

    /**
     *
     * @param doc 使用ObjectUtils.beanToMap方法把实体类转成map
     * @param tableName
     * @param oper oper的值为Command.ADD.toString()新增，Command.DELETE.toString()删除，Command.UPDATE.toString()修改
     * @return
     */
    public static String pushFile(String appName,LinkedHashMap doc,String tableName ,String oper) throws Exception{
        String returnStr = "success";
        JSONObject json = new JSONObject();
        json.put(DocumentConstants.DOC_KEY_CMD, oper);
        json.put(DocumentConstants.DOC_KEY_FIELDS, doc);
        JSONArray docsJsonArr = new JSONArray();
        docsJsonArr.put(json);//更新Push文档2为doc3
        //定义DocumentClient对象添加json格式doc数据批量提交
        DocumentClient documentClient = new DocumentClient(serviceClient);
        try {
            OpenSearchResult osr = documentClient.push(docsJsonArr.toString(), appName, tableName);
            if(osr.getResult().equalsIgnoreCase("true")){
                System.out.println("用户方推送无报错！\n以下为getTraceInfo推送请求Id:"+osr.getTraceInfo().getRequestId());
            }else{
                System.out.println("用户方推送报错！"+osr.getTraceInfo());
                returnStr = "fail";
            }
        } catch (OpenSearchException e) {
            e.printStackTrace();
        } catch (OpenSearchClientException e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    public static String commitFile(String appName,LinkedHashMap doc , String tableName) {
        String returnStr = "success";
        //定义DocumentClient对象添加json格式doc数据批量提交
        DocumentClient documentClient = new DocumentClient(serviceClient);
        // 把doc1加入缓存，并设为新增文档
        documentClient.add(doc);
        try {
            //执行提交新增操作，此处用于测试故单个提交延迟10s查看操作信息，也可在后面一次性提交执行操作
            OpenSearchResult osr = documentClient.commit(appName, tableName);
            //判断数据是否推送成功，主要通过判断2处，第一处判断用户方推送是否成功，第二处是应用控制台中有无报错日志
            //用户方推送成功后，也有可能在应用端执行失败，此错误会直接在应用控制台错误日志中生成，比如字段内容转换失败
            if(osr.getResult().equalsIgnoreCase("true")){
                System.out.println("用户方推送无报错！\n以下为getTraceInfo推送请求Id:"+osr.getTraceInfo().getRequestId());
            }else{
                System.out.println("用户方推送报错！"+osr.getTraceInfo());
                returnStr = "fail";
            }
        } catch (OpenSearchException e) {
            e.printStackTrace();
        } catch (OpenSearchClientException e) {
            e.printStackTrace();
        }
        return returnStr;
    }



}
