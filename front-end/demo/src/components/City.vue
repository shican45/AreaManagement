<template>
  <div class="container">
    <div class="title">
        当前：城市信息>{{ msg }}
    </div>
    <div v-show="!isEdit">
        <div class="buttonlist">
            <div class="button" @click="addCity()">添加</div>
            <div class="button" @click="editCity()" style="margin-left:30px">编辑</div>
            <div class="button" @click="deleteCity()" style="margin-left:30px">删除</div>
        </div>
        <div style="clear:both;margin-left:20px;margin-top:70px;">
            <table id="table">
                <tbody id="tableBody"> 
                    <tr>
                        <th style="width:10%;">序号</th>
                        <th style="width:40%;">城市名称</th>
                    </tr>
                    <tr @click="highlightRow($event)" v-for="(item, index) in tableData" :key="item.id">
                        <td>{{ index + 1 }}</td>
                        <td>{{ item.name }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div v-show="isEdit">
        <div style="margin: 35px;height:200px;width: 50%;border: 1px solid #999;">
            <div style="margin-top: 65px;margin-left:100px;">
                城市名称：
                <input v-model="city.name" autofocus="autofocus" style="margin-left:20px;width:400px;height:30px;" />
            </div>
            <div style="margin-top: 10px;margin-left: 250px;">
                <div class="button" @click="confirm()">确认</div>
                <div class="button" @click="cancel()" style="margin-left:30px">取消</div>
            </div>
        </div>
    </div>
  </div>
</template>
 
<script>
export default {
    name: "City",
    data() {
        return {
            msg: "列表",
            tableData: null,
            isEdit: false,
            addflag: false,
            oldCity: {
                id: null,
                name: '',
                areaId: null,
                addressList: null,
                createTime: null,
                modifedTime: null,
            },
            city: {
                id: null,
                name: '',
                areaId: null,
                addressList: null,
                createTime: null,
                modifedTime: null,
            },
        };
    },
    created(){
        this.$api.getCityList().then(res=>{
            if(res.status_code===1){
                this.tableData=res.data;
            }
        })
    },
    methods: {
        addCity(){
            this.msg = "添加";
            this.city.name = '';
            this.addflag = true;
            this.isEdit = true;
        },
        editCity(){
            if(this.oldCity.id == null){
                alert("请先选择一条数据");
                return;
            }
            this.msg = "编辑";
            this.city.id = JSON.parse(JSON.stringify(this.oldCity.id));
            this.city.name = JSON.parse(JSON.stringify(this.oldCity.name));
            this.addflag = false;
            this.isEdit = true;
        },
        deleteCity(){
            this.$api.deleteCity({
                id: this.oldCity.id,
                name:this.oldCity.name,
                createTime:null,
                modifedTime:null,
            }).then(res=>{
                if(res.status_code===1){
                    this.$router.go(0);
                }
            })
        },
        confirm(){
            if(this.addflag){
                this.addflag = false;
                if(this.city.name===''){
                    alert("城市名称不能为空！");
                    return;
                }else if(this.city.name.length>20){
                    alert("城市名称不能超过20个字符！");
                    return;
                }else if(this.tableData.some(item=>item.name===this.city.name)){
                    alert("该城市已存在！");
                    return;
                }
                this.$api.addCity({
                    id: null,
                    name:this.city.name,
                    areaId: null,
                    createTime:null,
                    modifedTime:null,
                }).then(res=>{
                    if(res.status_code===1){
                        this.isEdit = false;
                        this.msg = "列表";
                        this.$router.go(0);
                    }
                })
            }else{
                if(this.city.name===''){
                    alert("城市名称不能为空！");
                    return;
                }else if(this.city.name.length>20){
                    alert("城市名称不能超过20个字符！");
                    return;
                }else if(this.city.name===this.oldCity.name){
                    alert("城市名称不能与原名称相同！");
                    return;
                }else if(this.tableData.some(item=>item.name===this.city.name)){
                    alert("该城市已存在！");
                    return;
                }
                this.$api.updateCity(this.city).then(res=>{
                    if(res.status_code===1){
                        this.isEdit = false;
                        this.msg = "列表";
                        this.$router.go(0);
                    }
                })
            }
        
        },
        cancel(){
            this.msg = "列表";
            this.isEdit = false;
        },
        highlightRow(e) {
            if(e.target.parentNode.classList.contains('selected')){
                e.target.parentNode.classList.remove('selected');
                this.oldCity.id = null;
                this.oldCity.name = '';
                this.city = JSON.parse(JSON.stringify(this.oldCity));
            }else {
                let rows = e.target.parentNode.parentNode.getElementsByTagName('tr');
                let index = e.target.parentNode.rowIndex - 1;

                for (let i = 0; i < rows.length; i++) {
                    rows[i].classList.remove('selected');
                }

                e.target.parentNode.classList.add('selected');
                this.oldCity.id = JSON.parse(JSON.stringify(this.tableData[index].id));
                this.oldCity.name = e.target.parentNode.cells[1].innerText;
                this.city.name = JSON.parse(JSON.stringify(this.oldCity.name));
            }
        }
    }
};
</script>
 
<style scoped>
.title {
  border-bottom: 1.5px solid #a4a9acff;
  padding: 10px;
  margin: 10px;
  font-size: 22px;
  font-weight: bold;
  cursor: pointer;
}
.buttonlist {
  padding: 10px;
  margin: 35px;
  width: 100%;
  font-size: 18px;
}
.button {
  background-color: #0c5dc1ff;
  border: 1px solid #060a0aff;
  border-radius: 10px;
  font-weight: bold;
  padding: 10px;
  text-align: center;
  float: left;
  width: 100px;
  cursor: pointer;
}
table {
  width:50%;
  text-align:center;
  border-top: 1px solid #999;
  border-left: 1px solid #999;
  border-spacing: 0;
}
table td {
  padding: 10px 30px;
  border-bottom: 1px solid #999;
  border-right: 1px solid #999;
}
table th {
  padding: 10px 30px;
  border-bottom: 1px solid #999;
  border-right: 1px solid #999;
}
.selected {
  background-color: #d3d3d3;
}
</style>