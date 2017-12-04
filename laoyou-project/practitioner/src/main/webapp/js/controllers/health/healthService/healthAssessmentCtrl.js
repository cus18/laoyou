angular.module('controllers',[]).controller('healthAssessmentCtrl',
    ['$scope','$rootScope','$stateParams','$state','SaveHealthAssessmentAnswer','PractitionerUtil',
        function ($scope,$rootScope,$stateParams,$state,SaveHealthAssessmentAnswer,PractitionerUtil) {


            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;

                        // $scope.elderId = "2f06357d6c78463caea8df38b142f3d0";
                        // $scope.elderName = "刘涛";
                    })
            })

            $scope.healthAssessmentTemplateId = $stateParams.healthAssessmentTemplateId;

            $scope.answer = [];

            if($scope.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E924')
            {
                $scope.healthAssessmentTemplateName = "简易智力状态检查（MMSE）";
                $scope.question = {

                    questionName : "简易智力状态检查（MMSE）",
                    questions : [
                        {
                            questionPartName: "",
                            questionItems : [
                                {
                                    questionNumber : 1,
                                    questionType : "select",
                                    questionName : "文化程度",
                                    questionValues : ["文盲","小学","初中","高中","中专","大学","硕士","博士"],
                                    questionValue : "初中"
                                }

                            ]
                        },
                        {
                            questionPartName: "时间定向力",
                            questionItems : [
                                {
                                    questionNumber : 2,
                                    questionType : "radio",
                                    questionName : "今年的年份？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 3,
                                    questionType : "radio",
                                    questionName : "现在是什么季节？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 4,
                                    questionType : "radio",
                                    questionName : "今天是几号？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 5,
                                    questionType : "radio",
                                    questionName : "今天是星期几？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 6,
                                    questionType : "radio",
                                    questionName : "现在是几月份？",
                                    questionValue : true
                                }

                            ]
                        },
                        {
                            questionPartName: "地点定向力",
                            questionItems : [
                                {
                                    questionNumber : 7,
                                    questionType : "radio",
                                    questionName : "你能告诉我现在我们在哪里（省/市）？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 8,
                                    questionType : "radio",
                                    questionName : "你住在什么区（县）？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 9,
                                    questionType : "radio",
                                    questionName : "你住在什么街道 ？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 10,
                                    questionType : "radio",
                                    questionName : "我们现在是第几楼 ？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 11,
                                    questionType : "radio",
                                    questionName : "这儿是什么地方 ？",
                                    questionValue : true
                                }

                            ]
                        },
                        {
                            questionPartName: "即刻回忆：现在我要说三样东西的名称，在我讲完之后，请你重复说一遍，请你好好记住这三样东西，因为等一下要再问你的（请仔细说清楚，每一样东西一秒钟）。“皮球”“国旗”“树木”",
                            questionItems : [
                                {
                                    questionNumber : 12,
                                    questionType : "radio",
                                    questionName : "请你把这三样东西说一遍。",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 13,
                                    questionType : "radio",
                                    questionName : "第二样是什么东西？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 14,
                                    questionType : "radio",
                                    questionName : "第三样是什么东西？",
                                    questionValue : true
                                }
                            ]
                        },
                        {
                            questionPartName: "注意力与计算力：现在请你从100减去7，然后从所得的数目再减去7，如此一直计算下去，把每一个答案都告诉我，直到我说“停”为止。",
                            questionItems : [
                                {
                                    questionNumber : 15,
                                    questionType : "radio",
                                    questionName : "100 - 7 = ？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 16,
                                    questionType : "radio",
                                    questionName : "93 - 7 = ？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 17,
                                    questionType : "radio",
                                    questionName : "86 - 7 = ？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 18,
                                    questionType : "radio",
                                    questionName : "79 - 7 = ？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 19,
                                    questionType : "radio",
                                    questionName : "72 - 7 = ？",
                                    questionValue : true
                                },

                            ]
                        },
                        {
                            questionPartName: "现在请你告诉我，刚才我要你记住的三样东西是什么?",
                            questionItems : [
                                {
                                    questionNumber : 20,
                                    questionType : "radio",
                                    questionName : "第一样？（皮球）",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 21,
                                    questionType : "radio",
                                    questionName : "第一样？（国旗）",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 22,
                                    questionType : "radio",
                                    questionName : "第一样？（树木）",
                                    questionValue : true
                                }

                            ]
                        },
                        {
                            questionPartName: "命名",
                            questionItems : [
                                {
                                    questionNumber : 23,
                                    questionType : "radio",
                                    questionName : "请问这是什么?（展示手表）",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 24,
                                    questionType : "radio",
                                    questionName : "请问这是什么?（展示铅笔）",
                                    questionValue : true
                                }

                            ]
                        },
                        {
                            questionPartName: "语言重复",
                            questionItems : [
                                {
                                    questionNumber : 25,
                                    questionType : "radio",
                                    questionName : "语言重复：现在我要说一句话，请清楚地重复一遍，这句话是：“瑞雪兆丰年”",
                                    questionValue : true
                                }
                            ]
                        },
                        {
                            questionPartName: "阅读",
                            questionItems : [
                                {
                                    questionNumber : 26,
                                    questionType : "radio",
                                    questionName : "请照着这卡片所写的去做。（把写有“闭上您的眼睛”大字的卡片交给受访者）",
                                    questionValue : true
                                }
                            ]
                        },
                        {
                            questionPartName: "理解力：访问员说下面一段话，并给他一张空白纸，不要重复说明，也不要示范",
                            questionItems : [
                                {
                                    questionNumber : 27,
                                    questionType : "radio",
                                    questionName : "左手拿着这张纸",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 28,
                                    questionType : "radio",
                                    questionName : "把纸对折",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 29,
                                    questionType : "radio",
                                    questionName : "放在大腿上",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 30,
                                    questionType : "radio",
                                    questionName : "请你说一句完整的，有意义的句子（句子必须有主语，动词）记下所叙述句子的全文",
                                    questionValue : true
                                }
                            ]
                        },
                        {
                            questionPartName: "画图",
                            questionItems : [
                                {
                                    questionNumber : 31,
                                    questionType : "radio",
                                    questionName : "这是一张图，请你在同一张纸上照样把它画出来。（对：两个五边形的图案，交叉处形成个小四边形）",
                                    questionImg : "/practitioner/images/u435.png",
                                    questionValue : true
                                }
                            ]
                        }
                    ]
                }
            }
            else if($scope.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E922')
            {
                $scope.healthAssessmentTemplateName = "糖尿病风险评估";
                $scope.question =  {
                    questionName : "糖尿病风险评估",
                    questions : [
                        {
                            questionPartName: "",
                            questionItems : [
                                {
                                    questionNumber : 1,
                                    questionType : "select",
                                    questionName : "您的性别",
                                    questionValues : ["男","女"],
                                    questionValue : "男"
                                },
                                {
                                    questionNumber : 2,
                                    questionType : "input",
                                    questionName : "您的年龄是",
                                    questionValue : ""
                                },
                                {
                                    questionNumber : 3,
                                    questionType : "input",
                                    questionName : "您的BMI体重指数是",
                                    questionValue : ""
                                },
                                {
                                    questionNumber : 4,
                                    questionType : "input",
                                    questionName : "您的腰围是（单位：厘米）",
                                    questionValue : ""
                                },
                                {
                                    questionNumber : 5,
                                    questionType : "radio",
                                    questionName : "您平均每天的运动时间是否超过30分钟？",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 6,
                                    questionType : "radio",
                                    questionName : "您平均每天摄入水果的次数是否大于1次?",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 7,
                                    questionType : "radio",
                                    questionName : "您平均每天摄入蔬菜水果的次数是否大于1次?",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 8,
                                    questionType : "radio",
                                    questionName : "您是否长期服用过降血压药物?",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 9,
                                    questionType : "radio",
                                    questionName : "您最近一次检查的空腹血糖值是?",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 10,
                                    questionType : "select",
                                    questionName : "您的直系或旁系亲属中是否有被确诊为糖尿病?",
                                    questionValues : ["否","有 爷爷/姥爷、奶奶/姥姥、姑妈/姨妈、叔、伯/舅、表兄妹/堂兄妹(或其子女)","有 父母、兄弟姐妹、子女"],
                                    questionValue : "否"
                                }


                            ]
                        }
                    ]

                }
            }
            else if($scope.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E921')
            {
                $scope.healthAssessmentTemplateName = "脑卒中风险评估";
                $scope.question =  {
                    questionName : "脑卒中风险评估",
                    questions : [
                        {
                            questionPartName: "",
                            questionItems : [
                                {
                                    questionNumber : 1,
                                    questionType : "select",
                                    questionName : "您的性别",
                                    questionValues : ["男","女"],
                                    questionValue : "男"
                                },
                                {
                                    questionNumber : 2,
                                    questionType : "input",
                                    questionName : "您的年龄是",
                                    questionValue : ""
                                },
                                {
                                    questionNumber : 3,
                                    questionType : "input",
                                    questionName : "您的BMI体重指数是",
                                    questionValue : ""
                                },
                                {
                                    questionNumber : 4,
                                    questionType : "radio",
                                    questionName : "您在过去一个月还在抽烟吗 ?",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 5,
                                    questionType : "radio",
                                    questionName : "您患过糖尿病吗?",
                                    questionValue : true
                                },
                                {
                                    questionNumber : 6,
                                    questionType : "input",
                                    questionName : "您的收缩压是（单位：mmHg）",
                                    questionValue : ""
                                },
                                {
                                    questionNumber : 7,
                                    questionType : "input",
                                    questionName : "您的总胆固醇值是（单位：mmol/L）",
                                    questionValue : ""
                                }


                            ]
                        }
                    ]

                }
            }
            else if($scope.healthAssessmentTemplateId=='74F2219F-FDAD-5A4F-6972-40899E28E923')
            {
                $scope.healthAssessmentTemplateName = "中医体质评估";
                $scope.question =  {
                    questionName : "中医体质评估",
                    questions : [
                        {
                            questionPartName: "平和质",
                            questionItems : [
                                {
                                    questionNumber : 1,
                                    questionType : "select",
                                    questionName : "您的精力充沛吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 2,
                                    questionType : "select",
                                    questionName : "您容易疲乏吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 3,
                                    questionType : "select",
                                    questionName : "您说话声音低弱无力吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 4,
                                    questionType : "select",
                                    questionName : "您感到闷闷不乐、情绪低沉吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 5,
                                    questionType : "select",
                                    questionName : "您比一般人耐受不了寒冷(冬天的寒冷，夏天的冷空调、电扇等) 吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 6,
                                    questionType : "select",
                                    questionName : "您能适应外界自然和社会环境的变化吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 7,
                                    questionType : "select",
                                    questionName : "您容易失眠吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 8,
                                    questionType : "select",
                                    questionName : "您容易忘事（健忘）吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }


                            ]
                        },
                        {
                            questionPartName: "气虚质",
                            questionItems : [
                                {
                                    questionNumber : 9,
                                    questionType : "select",
                                    questionName : "您容易疲乏吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 10,
                                    questionType : "select",
                                    questionName : "您容易气短(呼吸短促，接不上气)吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 11,
                                    questionType : "select",
                                    questionName : "您容易心慌吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 12,
                                    questionType : "select",
                                    questionName : "您容易头晕或站起时晕眩吗 ?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 13,
                                    questionType : "select",
                                    questionName : "您比别人容易患感冒吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 14,
                                    questionType : "select",
                                    questionName : "您喜欢安静、懒得说话吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 15,
                                    questionType : "select",
                                    questionName : "您说话声音低弱无力吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 16,
                                    questionType : "select",
                                    questionName : "您活动量稍大就容易出虚汗吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }


                            ]
                        },
                        {
                            questionPartName: "阳虚质",
                            questionItems : [
                                {
                                    questionNumber : 17,
                                    questionType : "select",
                                    questionName : "您手脚发凉吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 18,
                                    questionType : "select",
                                    questionName : "您胃脘部、背部或腰膝部怕冷吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 19,
                                    questionType : "select",
                                    questionName : "您感到怕冷、衣服比别人穿得多吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 20,
                                    questionType : "select",
                                    questionName : "您比别人容易患感冒吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 21,
                                    questionType : "select",
                                    questionName : "您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗 ?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 22,
                                    questionType : "select",
                                    questionName : "您受凉或吃(喝)凉的东西后，容易腹泻(拉肚子)吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }

                            ]
                        },
                        {
                            questionPartName: "阴虚质",
                            questionItems : [
                                {
                                    questionNumber : 23,
                                    questionType : "select",
                                    questionName : "您感到手脚心发热吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 24,
                                    questionType : "select",
                                    questionName : "您感觉身体、脸上发热吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 25,
                                    questionType : "select",
                                    questionName : "您皮肤或口唇干吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 26,
                                    questionType : "select",
                                    questionName : "您口唇的颜色比一般人红吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 27,
                                    questionType : "select",
                                    questionName : "您容易便秘或大便干燥吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 28,
                                    questionType : "select",
                                    questionName : "您面部两颧潮红或偏红吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 29,
                                    questionType : "select",
                                    questionName : "您感到眼睛干涩吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 30,
                                    questionType : "select",
                                    questionName : "您感到口干咽燥、总想喝水吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }


                            ]
                        },
                        {
                            questionPartName: "痰湿质",
                            questionItems : [
                                {
                                    questionNumber : 31,
                                    questionType : "select",
                                    questionName : "您感到胸闷或腹部胀满吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 32,
                                    questionType : "select",
                                    questionName : "您感到身体沉重不轻松或不爽快吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 33,
                                    questionType : "select",
                                    questionName : "您腹部肥满松软吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 34,
                                    questionType : "select",
                                    questionName : "您有额部油脂分泌多的现象吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 35,
                                    questionType : "select",
                                    questionName : "您上眼睑比别人肿（上眼睑有轻微隆起的现象）吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 36,
                                    questionType : "select",
                                    questionName : "您嘴里有黏黏的感觉吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 37,
                                    questionType : "select",
                                    questionName : "您平时痰多，特别是咽喉部总感到有痰堵着吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 38,
                                    questionType : "select",
                                    questionName : "您舌苔厚腻或有舌苔厚厚的感觉吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }


                            ]
                        },
                        {
                            questionPartName: "湿热质",
                            questionItems : [
                                {
                                    questionNumber : 39,
                                    questionType : "select",
                                    questionName : "您面部或鼻部有油腻感或者油亮发光吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 40,
                                    questionType : "select",
                                    questionName : "您易生痤疮或疮疖吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 41,
                                    questionType : "select",
                                    questionName : "您感到口苦或嘴里有异味吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 42,
                                    questionType : "select",
                                    questionName : "您大便黏滞不爽、有解不尽的感觉吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 43,
                                    questionType : "select",
                                    questionName : "您小便时尿道有发热感、尿色浓(深)吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 44,
                                    questionType : "select",
                                    questionName : "您带下色黄(白带颜色发黄)吗?（限女性回答）？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 45,
                                    questionType : "select",
                                    questionName : "您的阴囊部位潮湿吗?（限男性回答）?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }

                            ]
                        },
                        {
                            questionPartName: "血淤质",
                            questionItems : [
                                {
                                    questionNumber : 46,
                                    questionType : "select",
                                    questionName : "您的皮肤在不知不觉中会出现青紫瘀斑(皮下出血)吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 47,
                                    questionType : "select",
                                    questionName : "您两颧部有细微红丝吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 48,
                                    questionType : "select",
                                    questionName : "您身体上有哪里疼痛吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 49,
                                    questionType : "select",
                                    questionName : "您面色晦黯、或容易出现褐斑吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 50,
                                    questionType : "select",
                                    questionName : "您容易有黑眼圈吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 51,
                                    questionType : "select",
                                    questionName : "您容易忘事（健忘）吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 52,
                                    questionType : "select",
                                    questionName : "您口唇颜色偏黯吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }

                            ]
                        },
                        {
                            questionPartName: "气郁质",
                            questionItems : [
                                {
                                    questionNumber : 53,
                                    questionType : "select",
                                    questionName : "您感到闷闷不乐、情绪低沉吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 54,
                                    questionType : "select",
                                    questionName : "您容易精神紧张、焦虑不安吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 55,
                                    questionType : "select",
                                    questionName : "您多愁善感、感情脆弱吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 56,
                                    questionType : "select",
                                    questionName : "您容易感到害怕或受到惊吓吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 57,
                                    questionType : "select",
                                    questionName : "您胁肋部或乳房胀痛吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 58,
                                    questionType : "select",
                                    questionName : "您无缘无故叹气吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 59,
                                    questionType : "select",
                                    questionName : "您咽喉部有异物感，且吐之不出、咽之不下吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }

                            ]
                        },
                        {
                            questionPartName: "特禀质",
                            questionItems : [
                                {
                                    questionNumber : 60,
                                    questionType : "select",
                                    questionName : "您没有感冒时也会打喷嚏吗？",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 61,
                                    questionType : "select",
                                    questionName : "您没有感冒时也会鼻塞、流鼻涕吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 62,
                                    questionType : "select",
                                    questionName : "您有因季节变化、温度变化或异味等原因而咳喘的现象吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 63,
                                    questionType : "select",
                                    questionName : "您容易过敏(对药物、食物、气味、花粉或在季节交替、气候变化时)吗 ?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 64,
                                    questionType : "select",
                                    questionName : "您的皮肤容易起荨麻疹(风团、风疹块、风疙瘩)吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 65,
                                    questionType : "select",
                                    questionName : "您的皮肤因过敏出现过紫癜(紫红色瘀点、瘀斑)吗 ? ",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                },
                                {
                                    questionNumber : 66,
                                    questionType : "select",
                                    questionName : "您的皮肤一抓就红，并出现抓痕吗?",
                                    questionValues : ["没有（根本不）","很少（有一点）","有时（有些）","经常（相当）","总是（非常）"],
                                    questionValue : "有时（有些）"
                                }
                            ]
                        }
                    ]

                }
            }


            $scope.chooseRadioAnswer = function(num,value){
                $scope.answer[num] = value;
            }

            $scope.submit = function(){
                angular.forEach($scope.question.questions, function(value, index, array){
                    angular.forEach(value.questionItems, function(val,ind,arr){
                        if($scope.answer[val.questionNumber]==undefined)
                        {
                            $scope.answer[val.questionNumber] = val.questionValue;
                        }

                    })
                });
                console.log($scope.answer)
                SaveHealthAssessmentAnswer.save({healthAssessmentTemplateId:$scope.healthAssessmentTemplateId,
                    healthAssessmentTemplateName: $scope.healthAssessmentTemplateName,
                    healthAssessmentData:$scope.answer.toString(),
                    elderId : $scope.elderId,
                    elderName : $scope.elderName},function(data){
                     PractitionerUtil.checkResponseData(data);
                     console.log(data)
                     $state.go("healthService",{firstMenu:'healthAssessment',secondMenu:''});
                })
            }


        }])
