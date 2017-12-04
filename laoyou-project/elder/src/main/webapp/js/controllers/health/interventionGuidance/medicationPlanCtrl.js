/**
 * Created by 郑强丽 on 2017/5/26.
 */
angular.module('controllers',[]).controller('medicationPlanCtrl',
    ['$scope','$rootScope','$stateParams','$state','InsertMedicationPlan','UpdateMedicationPlan',
        'DeleteMedicationPlan','GetMedicationPlanByID','$timeout',
        function($scope,$rootScope,$stateParams,$state,InsertMedicationPlan,UpdateMedicationPlan,
                 DeleteMedicationPlan,GetMedicationPlanByID,$timeout){

            $scope.loadingStatus = true;

            $scope.pageType = $stateParams.pageType;
            $scope.listId = $stateParams.listId;


            if($stateParams.editable == 'true'){
                $scope.editable = true;
            }else if($stateParams.editable == 'false'){
                $scope.editable = false;
            }

            $scope.medicationPlan = {};
            $scope.medicationPlan.timeData = [];
            $scope.medicationPlan.repeatData = [];


            $timeout(function(){
                if($scope.pageType == 'checkPlan'){
                    GetMedicationPlanByID.save({id:$scope.listId},function(data){
                        $scope.loadingStatus = false;

                        var data = data.responseData;
                        var repeatData = data.repeat;
                        var timeData = data.usageTime;
                        $scope.medicationPlan.drugName = data.drugName;
                        $scope.medicationPlan.dose = data.dose;
                        $scope.medicationPlan.remark = data.remark;
                        $scope.medicationPlan.startTime = data.startTime;
                        $scope.medicationPlan.endTime = data.endTime;
                        $scope.medicationPlan.timeData = timeData.substring(0,timeData.length-1).split(';');
                        $scope.medicationPlan.repeatData = repeatData.substring(0,repeatData.length-1).split(';');
                        $scope.isChecked = function(id){
                            return $scope.medicationPlan.repeatData.indexOf(id) >= 0;
                        };
                    });
                }

                /*药品名称*/
                var medicineName = ['阿巴卡韦','阿苯达唑','阿达帕林','阿法骨化醇','格华止（盐酸二甲双胍片）','金纳多','达美康（格列齐特缓释片）','糖适平（格列喹酮片）'];
                $('#addMedicineName').mobiscroll().scroller({
                    theme: 'ios',
                    lang:'zh',
                    display: 'bottom',
                    setText:'保存',
                    cancelText:'取消',
                    headerText:'药品名称',
                    wheels: [
                        [{
                            circular: false,
                            data: medicineName
                        }]
                    ]
                });


                /*药物剂量*/
                var medicineFirstDosage = [];
                for(var i = 1; i < 501; i++){
                    medicineFirstDosage.push(i);
                }
                var medicineSecondDosage = ['.0','.5'];
                var medicineUnit = ['mg','g','片','ml','杯'];
                $('#addMedicineDosage').mobiscroll().scroller({
                    theme: 'ios',
                    lang:'zh',
                    display: 'bottom',
                    setText:'保存',
                    cancelText:'取消',
                    headerText:'药物剂量',
                    wheels: [
                        [{
                            circular: true,
                            data: medicineFirstDosage
                        }, {
                            circular: false,
                            data: medicineSecondDosage
                        }, {
                            circular: false,
                            data: medicineUnit
                        }]
                    ],
                    parseValue:function(){
                        return ['1','.0','片']
                    }
                });

                /*用药时间*/
                $('#addMedicineTime').mobiscroll().time({
                    theme: 'ios',
                    lang:'zh',
                    display: 'bottom',
                    setText:'保存',
                    cancelText:'取消',
                    headerText:'用药时间',
                    onSet:function(data){
                        $('#addMedicineTime').val('点击继续添加').css('color','#999');
                        $scope.medicationPlan.timeData.push(data.valueText);
                        $scope.$apply();
                    }
                });

                /*自定义时间*/
                $('#customInputStart').mobiscroll().calendar({
                    theme: 'ios',
                    lang:'zh',
                    display: 'bottom',
                    setText:'保存',
                    cancelText:'取消',
                    dateFormat:'yy-mm-dd',
                    headerText:'开始时间',
                    min:new Date()
                });

                $('#customInputEnd').mobiscroll().calendar({
                    theme: 'ios',
                    lang:'zh',
                    display: 'bottom',
                    setText:'保存',
                    cancelText:'取消',
                    dateFormat:'yy-mm-dd',
                    headerText:'结束时间',
                    min:new Date()
                });

                $scope.delTime = function($event){
                    var target = $event.target;
                    $(target).parent().remove();
                    var time = $(target).parent().text();
                    var index = $scope.medicationPlan.timeData.indexOf(time);
                    if(index > -1){
                        $scope.medicationPlan.timeData.splice(index,1)
                    }
                };

                /*重复*/
                $scope.medicationPlan.repeatList = [
                    {'id':'0','value':'repeat0','name':'周日'},
                    {'id':'1','value':'repeat1','name':'周一'},
                    {'id':'2','value':'repeat2','name':'周二'},
                    {'id':'3','value':'repeat3','name':'周三'},
                    {'id':'4','value':'repeat4','name':'周四'},
                    {'id':'5','value':'repeat5','name':'周五'},
                    {'id':'6','value':'repeat6','name':'周六'},
                    {'id':'7','value':'repeat7','name':'每天'}
                ];


                $scope.repeatSelection = function($event,id){
                    var checkbox = $event.target;
                    var checked = checkbox.checked;
                    if(checked){
                        $scope.medicationPlan.repeatData.push(id);
                    }else{
                        var idx = $scope.medicationPlan.repeatData.indexOf(id);
                        $scope.medicationPlan.repeatData.splice(idx,1);
                    }
                };

            },100);

            $scope.medicationPlan.delSureFun = function(){
                $scope.medicationPlan.delSure = false;
            }


            connectWebViewJavascriptBridge(function() {
                window.WebViewJavascriptBridge.callHandler(
                    'getElderInfo','',function(responseData) {
                        var dataValue = JSON.parse(responseData);
                        $scope.elderId = dataValue.elderId;
                        $scope.elderName = dataValue.elderName;

                        // $scope.elderId = '100000002693';
                        // $scope.elderName = '浦声波';


                        /*提交*/
                        var submitOnOff = true;  //防止多次提交
                        $scope.medicationPlan.submitForm = function(isValid){
                            if(isValid){

                                if($scope.medicationPlan.repeatData.join(';').length > 0){

                                    var repeatData = $scope.medicationPlan.repeatData.sort();
                                    if(repeatData.indexOf('7') > 0){
                                        repeatData = ['7'];
                                    }
                                    if(submitOnOff) {
                                        if ($scope.pageType == 'newPlan') {
                                            if (new Date($scope.medicationPlan.endTime) > new Date($scope.medicationPlan.startTime)) {
                                                submitOnOff = false;
                                                InsertMedicationPlan.save({
                                                    drugName: $scope.medicationPlan.drugName,
                                                    dose: $scope.medicationPlan.dose,
                                                    usageTime: $scope.medicationPlan.timeData.join(';') + ';',
                                                    repeat: repeatData.join(';') + ';',
                                                    startTime: $scope.medicationPlan.startTime,
                                                    endTime: $scope.medicationPlan.endTime,
                                                    remark: $scope.medicationPlan.remark,
                                                    elderID: $scope.elderId
                                                }, function (data) {
                                                    // console.log(data)
                                                    $state.go('interventionGuidance', {
                                                        firstMenu: 'medicineIntervention',
                                                        secondMenu: 'interventionPlan'
                                                    });
                                                })
                                            }
                                        } else if ($scope.pageType == 'checkPlan') {
                                            submitOnOff = false;
                                            UpdateMedicationPlan.save({
                                                id: $scope.listId,
                                                drugName: $scope.medicationPlan.drugName,
                                                dose: $scope.medicationPlan.dose,
                                                usageTime: $scope.medicationPlan.timeData.join(';') + ';',
                                                repeat: repeatData.join(';') + ';',
                                                startTime: $scope.medicationPlan.startTime,
                                                endTime: $scope.medicationPlan.endTime,
                                                remark: $scope.medicationPlan.remark,
                                                elderID: $scope.elderId
                                            }, function (data) {
                                                $state.go('interventionGuidance', {
                                                    firstMenu: 'medicineIntervention',
                                                    secondMenu: 'interventionPlan'
                                                });
                                            })
                                        }
                                    }
                                }
                            }
                        }

                    })
            })

            /*删除用药干预*/
            $scope.medicationPlan.delMedicationPlan = function(){
                DeleteMedicationPlan.save({id:$scope.listId},function(data){
                    $state.go('interventionGuidance',{firstMenu:'medicineIntervention',secondMenu:'interventionPlan'});
                })
            }

        }]);












