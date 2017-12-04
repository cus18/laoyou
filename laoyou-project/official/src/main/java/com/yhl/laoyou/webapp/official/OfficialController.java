package com.yhl.laoyou.webapp.official;

import com.yhl.laoyou.common.dto.PageParamDTO;
import com.yhl.laoyou.common.dto.ResponseDTO;
import com.yhl.laoyou.common.utils.CheckParam;
import com.yhl.laoyou.common.utils.StringUtils;
import com.yhl.laoyou.modules.sys.entity.PaginationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by sunxiao on 2017/7/25.
 */
@Controller
@RequestMapping(value = "official/")
public class OfficialController {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @RequestMapping(value = "getNews", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResponseDTO<Map> getNews(@RequestBody PageParamDTO<Article> pageParamDto) {
        ResponseDTO<Map> responseDTO = new ResponseDTO<Map>();
        String catalogId = pageParamDto.getRequestData().getCategory().getId();
        String articleId = pageParamDto.getRequestData().getId();
        Query query1 = new Query().addCriteria(Criteria.where("_id").is(catalogId))
                .addCriteria(Criteria.where("delFlag").is("0"));
        List<Category> data1 = mongoTemplate.find(query1, Category.class,"officialCategory");//本目录信息
        Map map = new LinkedHashMap();
        if(data1 != null && data1.size()>0){
            Query query2 = new Query().addCriteria(Criteria.where("_id").is(data1.get(0).getParentId()))
                    .addCriteria(Criteria.where("delFlag").is("0"));
            List<Category> data2 = mongoTemplate.find(query2, Category.class,"officialCategory");//父目录信息
            if(data2 != null && data2.size()>0){
                map.put("primaryName",data2.get(0).getName());
            }
            map.put("twoLevelName",data1.get(0).getName());
            Query query3 = new Query().addCriteria(Criteria.where("parent._id").is(data1.get(0).getParentId()))
                    .addCriteria(Criteria.where("delFlag").is("0"));
            query3.with(new Sort(new Sort.Order(Sort.Direction.ASC, "sort")));
            List<Category> data3 = mongoTemplate.find(query3, Category.class,"officialCategory");//同级目录信息
            map.put("twoLevelCategory",data3);
        }
        List<Article> data = new ArrayList<>();
        Query query = null;
        if(StringUtils.isNotNull(articleId)){
            query = new Query().addCriteria(Criteria.where("_id").is(articleId));
        }else{
            query = new Query().addCriteria(Criteria.where("category._id").is(catalogId));
        }
        query.addCriteria(Criteria.where("delFlag").is("0"))
                .with(new Sort(new Sort.Order(Sort.Direction.DESC, "createDate")));
        long totalCount = mongoTemplate.count(query,"officialNews");
        PaginationVo<Article> page = new PaginationVo<Article>(Integer.parseInt(pageParamDto.getPageNo()),
                Integer.parseInt(pageParamDto.getPageSize()), totalCount);
        query.skip(page.getFirstResult());// skip相当于从那条记录开始
        query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
        data = mongoTemplate.find(query, Article.class,"officialNews");
        map.put("news",data);
        map.put("totalCount",totalCount);
        responseDTO.setResponseData(map);
        return responseDTO;
    }

    @RequestMapping(value = "getCategory", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResponseDTO<Map> getCategory() {
        ResponseDTO<Map> responseDTO = new ResponseDTO<Map>();
        Query query1 = new Query().addCriteria(Criteria.where("parent._id").is("2dbabffbebf24c8ea404fa172b991da6"))
                .addCriteria(Criteria.where("delFlag").is("0"));
        List<Category> data1 = mongoTemplate.find(query1, Category.class,"officialCategory");
        Map map = new LinkedHashMap();
        for(Category category : data1){
            Query query2 = new Query().addCriteria(Criteria.where("parent._id").is(category.getId()))
                    .addCriteria(Criteria.where("delFlag").is("0"));
            query2.with(new Sort(new Sort.Order(Sort.Direction.ASC, "sort")));
            List<Category> data2 = mongoTemplate.find(query2, Category.class,"officialCategory");
            map.put(category.getName(),data2);
        }
        responseDTO.setResponseData(map);
        return responseDTO;
    }

    @RequestMapping(value = "searchNews", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResponseDTO<Map> searchNews(@RequestBody PageParamDTO pageParamDto) {
        ResponseDTO<Map> responseDTO = new ResponseDTO<Map>();
        try {
            CheckParam.checkPageParamDto(pageParamDto);
            String search = (String) pageParamDto.getRequestData();//URLDecoder.decode(search,"utf-8");
            Query queryCategory = new Query().addCriteria(Criteria.where("name").is("新闻中心"))
                    .addCriteria(Criteria.where("delFlag").is("0"));
            List<Category> categoryList = mongoTemplate.find(queryCategory, Category.class,"officialCategory");
            Map map = new HashMap();
            if(categoryList!=null&&categoryList.size()>0){
                Query queryTwoLevelCategory = new Query().addCriteria(Criteria.where("parent._id").is(categoryList.get(0).getId()))
                        .addCriteria(Criteria.where("delFlag").is("0"));
                List<Category> twoLevelCategoryList = mongoTemplate.find(queryTwoLevelCategory, Category.class,"officialCategory");
                if(twoLevelCategoryList!=null&&twoLevelCategoryList.size()>0){
                    Query query = new Query();
                    Criteria or1 = new Criteria().orOperator(Criteria.where("title").regex(".*?" + search + ".*")
                            , Criteria.where("articleData.content").regex(".*?" + search + ".*"));
                    List criteriaList = new ArrayList();
                    for(Category category : twoLevelCategoryList){
                        Criteria criteria = Criteria.where("category._id").is(category.getId());
                        criteriaList.add(criteria);
                    }
                    Criteria or2 = new Criteria().orOperator((Criteria[])criteriaList.toArray(new Criteria[criteriaList.size()]));
                    query.addCriteria(new Criteria().andOperator(or1,or2));
                    query.addCriteria(Criteria.where("delFlag").is("0"));
                    long totalCount = mongoTemplate.count(query,"officialNews");
                    PaginationVo<Article> page = new PaginationVo<Article>(Integer.parseInt(pageParamDto.getPageNo()),
                            Integer.parseInt(pageParamDto.getPageSize()), totalCount);
                    query.skip(page.getFirstResult());// skip相当于从那条记录开始
                    query.limit(Integer.parseInt(pageParamDto.getPageSize()));// 从skip开始,取多少条记录
                    List articleList = mongoTemplate.find(query, Article.class,"officialNews");
                    map.put("newsList",articleList);
                    map.put("totalCount",totalCount);
                }
            }
            responseDTO.setResponseData(map);
            responseDTO.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setErrorInfo("exception!");
        }
        return responseDTO;
    }
}
