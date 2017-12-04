angular.module('controllers',[]).controller('questionCtrl',
    ['$scope','$stateParams','SaveBasicInfo','SaveServiceInfo',
        function ($scope,$stateParams,SaveBasicInfo,SaveServiceInfo) {

    $scope.value = {};
    $scope.value.type = $stateParams.type;

    var init = function(){
        if($scope.value.type=="basicInfo")
        {
            $scope.identity = {
                a:false,
                b:false,
                c:false,
                d:false
            }
            $scope.answer = {
                inputerName:"",
                street:"",
                community:"",
                name:"",
                idCard:"",
                age:"",
                gendar:"",
                mobile:"",
                phone:"",
                nation:"",
                origin:"",
                political:"",
                cultural:"",
                marital:"",
                identity:"",
                householdType:"",
                censusStatus:"",
                livingConditions:"",
                permanentArea:"",
                permanentAddress:"",
                livingAddress:"",
                communityPhone:"",
                disability:"",
                disabilityCard:"",
                relativesName1:"",
                relativeRelationship1:"",
                relativesPhone1:"",
                relativesArea1:"",
                relativesAddress1:"",
                relativesName2:"",
                relativeRelationship2:"",
                relativesPhone2:"",
                relativesArea2:"",
                relativesAddress2:"",
                villageCadres:"",
                villageCadresPhone:"",
                villageCadresAddress:"",
                partyMemberVolunteers:"",
                partMemberVolunteersPhone:"",
                partMemberVolunteersAddress:"",
                remark:"",
                formHolder:"",
                auditor:""
            };
        }
        else if($scope.value.type=="serviceInfo")
        {
            $scope.moneySource = {
                moneyA:false,
                moneyB:false,
                moneyC:false,
            }
            $scope.needPartyServices = {
                a:false,
                b:false,
                c:false,
                d:false,
                e:false
            }
            $scope.sickness = {
                a:false,
                b:false,
                c:false,
                d:false,
                e:false,
                f:false
            }
            $scope.needHealthServices = {
                a:false,
                b:false,
                c:false
            }
            $scope.needMedicalServices = {
                a:false,
                b:false,
                c:false,
                d:false
            }
            $scope.whatPension = {
                a:false,
                b:false,
                c:false,
                d:false,
                e:false
            }
            $scope.needLifeServices = {
                a:false,
                b:false,
                c:false,
                d:false,
                e:false,
                f:false,
                g:false,
                h:false,
                i:false
            }

            $scope.answer = {
                inputerName:"",
                street:"",
                name:"",
                age:"",
                idCard:"",
                lastJob:"",
                lastJobE:"",
                moneySourceA:"",
                moneySourceB:"",
                moneySourceC:"",
                ifPartyMembers:"",
                needPartyServices:"",
                needPartyServicesE:"",
                sickness:"",
                sicknessF:"",
                needFirstAidServices:"",
                needHealthServices:"",
                needMedicalServices:"",
                whatPension:"",
                whatPensionE:"",
                needLifeServices:"",
                needEmergencyServices:false,
                needGPSServices:false,
                needPhoneServices:"",
                whatNeedPhoneServices:"",
                investigator:"",
            };
        }
    }

    init();

    $scope.submit = function(){

        if($scope.value.type=="basicInfo"){
            var identityInfo="";
            if($scope.identity.a)
            {
                identityInfo = identityInfo + "普通老人,"
            }
            if($scope.identity.b)
            {
                identityInfo = identityInfo + "低保老人,"
            }
            if($scope.identity.c)
            {
                identityInfo = identityInfo + "失能失智老人,"
            }
            if($scope.identity.d)
            {
                identityInfo = identityInfo + "其它"
            }

            $scope.answer.identity = identityInfo;

            if($scope.answer.inputerName==""||$scope.answer.name=="")
            {
                alert("请输入录入者姓名或调查用户姓名");
                return;
            }
            console.log($scope.answer);
            SaveBasicInfo.save($scope.answer,function(data){
                if(data.result=="success")
                {
                    alert("录入成功");
                    init();
                }
                else {
                    alert("录入失败");
                }
            })

        }
        else if($scope.value.type=="serviceInfo")
        {
            if($scope.answer.inputerName==""||$scope.answer.name=="")
            {
                alert("请输入录入者姓名或者被调查者姓名");
                return;
            }

            if($scope.answer.lastJob=="e"){
                $scope.answer.lastJob="";
            }

            if($scope.moneySource.moneyA)
            {
                if($scope.answer.moneySourceA=="")
                {
                    $scope.answer.moneySourceA = 1;
                }

            }
            if($scope.moneySource.moneyB)
            {
                if($scope.answer.moneySourceB=="")
                {
                    $scope.answer.moneySourceB = 1;
                }
            }
            if($scope.moneySource.moneyC)
            {
                if($scope.answer.moneySourceC=="")
                {
                    $scope.answer.moneySourceC = 1;
                }
            }

            var needPartyServices = "";
            var needPartyServicesE = "";
            if($scope.needPartyServices.a)
            {
                needPartyServices = needPartyServices + "a";
            }
            if($scope.needPartyServices.b)
            {
                needPartyServices = needPartyServices + "b";
            }
            if($scope.needPartyServices.c)
            {
                needPartyServices = needPartyServices + "c";
            }
            if($scope.needPartyServices.d)
            {
                needPartyServices = needPartyServices + "d";
            }
            if($scope.needPartyServices.e)
            {
                if($scope.needPartyServicesE=="")
                {
                    needPartyServicesE="1";
                }
            }
            $scope.answer.needPartyServices = needPartyServices;
            $scope.answer.needPartyServicesE = needPartyServicesE;

            var sickness = "";
            var sicknessF = "";
            if($scope.sickness.a)
            {
                sickness = sickness + "a";
            }
            if($scope.sickness.b)
            {
                sickness = sickness + "b";
            }
            if($scope.sickness.c)
            {
                sickness = sickness + "c";
            }
            if($scope.sickness.d)
            {
                sickness = sickness + "d";
            }
            if($scope.sickness.e)
            {
                sickness = sickness + "e";
            }
            if($scope.sickness.f)
            {
                if($scope.sicknessF=="")
                {
                    sicknessF="1";
                }
            }
            $scope.answer.sickness = sickness;
            $scope.answer.sicknessF = sicknessF;


            var needHealthServices = "";
            if($scope.needHealthServices.a)
            {
                needHealthServices = needHealthServices + "a";
            }
            if($scope.needHealthServices.b)
            {
                needHealthServices = needHealthServices + "b";
            }
            if($scope.needHealthServices.c)
            {
                needHealthServices = needHealthServices + "c";
            }
            $scope.answer.needHealthServices = needHealthServices;

            var needMedicalServices = "";
            if($scope.needMedicalServices.a)
            {
                needMedicalServices = needMedicalServices + "a";
            }
            if($scope.needMedicalServices.b)
            {
                needMedicalServices = needMedicalServices + "b";
            }
            if($scope.needMedicalServices.c)
            {
                needMedicalServices = needMedicalServices + "c";
            }
            if($scope.needMedicalServices.d)
            {
                needMedicalServices = needMedicalServices + "d";
            }
            $scope.answer.needMedicalServices = needMedicalServices;

            var whatPension = "";
            var whatPensionE = "";
            if($scope.whatPension.a)
            {
                whatPension = whatPension + "a";
            }
            if($scope.whatPension.b)
            {
                whatPension = whatPension + "b";
            }
            if($scope.whatPension.c)
            {
                whatPension = whatPension + "c";
            }
            if($scope.whatPension.d)
            {
                whatPension = whatPension + "d";
            }
            if($scope.whatPension.e)
            {
                if($scope.whatPensionE=="")
                {
                    whatPensionE="1";
                }
            }
            $scope.answer.whatPension = whatPension;
            $scope.answer.whatPensionE = whatPensionE;

            var needLifeServices = "";
            if($scope.needLifeServices.a)
            {
                needLifeServices = needLifeServices + "a";
            }
            if($scope.needLifeServices.b)
            {
                needLifeServices = needLifeServices + "b";
            }
            if($scope.needLifeServices.c)
            {
                needLifeServices = needLifeServices + "c";
            }
            if($scope.needLifeServices.d)
            {
                needLifeServices = needLifeServices + "d";
            }
            if($scope.needLifeServices.e)
            {
                needLifeServices = needLifeServices + "e";
            }
            if($scope.needLifeServices.f)
            {
                needLifeServices = needLifeServices + "f";
            }
            if($scope.needLifeServices.g)
            {
                needLifeServices = needLifeServices + "g";
            }
            if($scope.needLifeServices.h)
            {
                needLifeServices = needLifeServices + "h";
            }
            if($scope.needLifeServices.i)
            {
                needLifeServices = needLifeServices + "i";
            }
            $scope.answer.needLifeServices = needLifeServices;

            if($scope.answer.needEmergencyServices){
                $scope.answer.needEmergencyServices = "是";
            }else {
                $scope.answer.needEmergencyServices = "";
            }

            if($scope.answer.needGPSServices){
                $scope.answer.needGPSServices = "是";
            }else {
                $scope.answer.needGPSServices = "";
            }

            console.log($scope.answer);

            SaveServiceInfo.save($scope.answer,function(data){
                if(data.result=="success")
                {
                    alert("录入成功");
                    init();
                }
                else {
                    alert("录入失败");
                }
            })
        }

    }


}])