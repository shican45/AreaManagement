<template>
  <div class="container">
    <div class="title">当前：域区管理>{{ msg }}
    </div>
    <div v-show="!isEdit">
        <div style="margin-top: 10px;margin-left:25px;">
          区域名称：
          <input v-model="findArea.name" class="findAreaInput" />
          区域城市：
          <input v-model="findArea.city" class="findAreaInput" />
          地址：
          <input v-model="findArea.address" class="findAreaInput" />
          创建时间：
          <el-date-picker
            v-model="time"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
          <div class="button" @click="searchArea()" style="margin-left:20px;">搜索</div>
        </div>
        <div class="buttonlist">
            <div class="button" @click="addArea()">添加</div>
            <div class="button" @click="viewArea()" style="margin-left:30px">查看</div>
            <div class="button" @click="editArea()" style="margin-left:30px">编辑</div>
            <div class="button" @click="deleteArea()" style="margin-left:30px">删除</div>
        </div>
        <div style="clear:both;margin-left:20px;margin-top:20px;">
            <table id="table">
                <tbody id="tableBody">
                    <tr>
                        <th style="width:5%;">序号</th>
                        <th style="width:15%;">区域名称</th>
                        <th style="width:20%;">区域城市</th>
                        <th style="width:40%;">地址</th>
                        <th style="width:20%;">创建时间</th>
                    </tr>
                    <tr @click="highlightRow($event)" v-for="item in tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" :key="item.id">
                        <td>{{ item.id }}</td>
                        <td>{{ item.name }}</td>
                        <td>{{ item.cityListInnerText }}</td>
                        <td >{{ item.addressListInnerText }}</td>
                        <td>{{ item.createTime }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <el-pagination
              background
              layout="prev, pager, next, total"
              :total="tableData.length"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-size="pageSize"
              style="text-align: right;margin-right: 130px;">
      </el-pagination>
    </div>
    <div v-show="isEdit">
      <div style="margin-top: 35px;margin-left:35px;">
        区域名称：
        <input v-model="area.name" style="margin-left:0px;width:400px;height:20px;" />
      </div>

      <div class="addButton" @click="addCity()" style="margin-left:35px;margin-top: 20px;" v-show="!viewflag">+新增区域城市</div>
      
      <div v-show="addflag" v-for="(item, index) in cityCount" :id="index" class="cityListDiv">
        <div class="addButton" @click="deleteCity(index)" style="float:right;width: 50px;margin-top: 0px;">删除</div>
        <div style="margin-top: 10px;margin-left:10px;float: left;">
          区域城市{{index + 1}}：
          <select v-model="area.cityList[index].id" class="select">
            <option v-for="(city) in cityList" :key="city.id" :value="city.id">
              {{ city.name }}
            </option>
          </select>
        </div>
        <div v-for="(item, addressIndex) in cityIndex[index]" :id="addressIndex" style="margin-top: 10px;margin-left:10px;float: left;">
          地址{{addressIndex + 1}}：
          <input v-model="area.cityList[index].addressList[addressIndex].address" style="margin-left:0px;width:400px;height:20px;" />
          <div class="addButton" @click="addAddress(index)" style="margin-left:10px;width: 30px;">+</div>
          <div class="addButton" @click="subtractAddress(index)" style="margin-left:10px;width: 30px;">-</div>
        </div>
      </div>

      <div v-show="!addflag" v-for="(item, index) in area.cityList" :key="item.id" class="cityListDiv">
        <div class="addButton" @click="deleteCity2(index)" style="float:right;width: 50px;margin-top: 0px;" v-show="!viewflag">删除</div>
        <div style="margin-top: 20px;margin-left:10px;float: left;">
          区域城市{{index + 1}}：
          <select v-model="item.id" class="select" v-bind:disabled="viewflag" >
            <option v-for="(city) in cityList" :key="city.id" :value="city.id">
              {{ city.name }}
            </option>
          </select>
          <div v-for="(item, addressIndex) in item.addressList.length" :id="addressIndex" style="margin-top: 10px;margin-left:10px;">
            地址{{addressIndex + 1}}：
            <input v-model="area.cityList[index].addressList[addressIndex].address" v-bind:disabled="viewflag" style="margin-left:0px;width:400px;height:20px;" />
            <div class="addButton" @click="addAddress(index)" v-show="!viewflag" style="margin-left:10px;width: 30px;">+</div>
            <div class="addButton" @click="subtractAddress(index)" v-show="!viewflag" style="margin-left:10px;width: 30px;">-</div>
          </div>
        </div>
      </div>

      <div style="margin-top: 10px;margin-left: 250px;">
        <div class="button" @click="confirm()" v-show="!viewflag">确认</div>
        <div class="button" @click="cancel()" style="margin-left:30px">取消</div>
      </div>
    </div>
  </div>
</template>
 
<script>
export default {
  name: "Area",
  data() {
      return {
      msg: "列表",
      tableData: null,
      cityList: null,
      currentPage: 1,
      pageSize: 2,
      isEdit: false,
      addflag: false,
      cityCount: 0,
      cityIndex: [],
      selected: '',
      viewflag: false,
      time: [],
      findArea: {
          name: '',
          city: '',
          address: '',
          startTime: '',
          endTime: '',
      },
      oldArea: {
          id: null,
          name: '',
          cityList: [],
          createTime: null,
          modifedTime: null,
      },
      area: {
          id: null,
          name: '',
          cityList: [],
          createTime: null,
          modifedTime: null,
      },
    };
  },
  created(){
    this.$api.getAreaList().then(res=>{
      if(res.status_code===1){
        this.tableData=res.data;
        console.log("tableData", this.tableData);
        for(let i = 0; i < this.tableData.length; i++){
          let cityNames = [];
          let addressNames = [];
          let addressNum = 1;
          for(let city of this.tableData[i].cityList){
            cityNames.push(city.name);
            for(let j = 1; j < city.addressList.length + 1; j++){
              addressNames.push(addressNum + '.' + city.addressList[j - 1].address);
              addressNum++;
            }
          }
          this.tableData[i].cityListInnerText = cityNames.join('、');
          this.tableData[i].addressListInnerText = addressNames.join('\n');
        }
      }
    })
    this.$api.getCityList().then(res=>{
      if(res.status_code===1){
        this.cityList=res.data;
        console.log("cityList", this.cityList);
      }
    })
  },
  methods: {
    handleCurrentChange(currentPage) {
        this.currentPage = currentPage;
      },
    searchArea() {
      if(this.time == null){
        this.time = [];
      }
      this.findArea.startTime = this.time[0];
      this.findArea.endTime = this.time[1];

      this.$api.findArea(this.findArea).then(res=>{
        if(res.status_code===1){
          this.tableData=res.data;
          
          for(let i = 0; i < this.tableData.length; i++){
            let cityNames = [];
            let addressNames = [];
            let addressNum = 1;
            for(let city of this.tableData[i].cityList){
              cityNames.push(city.name);
              for(let j = 1; j < city.addressList.length + 1; j++){
                addressNames.push(addressNum + '.' + city.addressList[j - 1].address);
              addressNum++;
              }
            }
            this.tableData[i].cityListInnerText = cityNames.join('、');
            this.tableData[i].addressListInnerText = addressNames.join('\n');
            console.log("tableData", this.tableData);
          }
        }
      })
    },
    addArea(){
      this.addflag = true;
      this.msg = "新增/编辑页";
      this.isEdit = true;

      let rows = document.getElementsByTagName('tr');
      for (let i = 0; i < rows.length; i++) {
        rows[i].classList.remove('selected');
      }
      this.oldArea.id = null;
      this.oldArea.name = '';
      this.oldArea.cityList = [];
      this.oldArea.createTime = null;
      this.area = JSON.parse(JSON.stringify(this.oldArea));
    },
    deleteCity(index){
      if(this.cityCount > 0){
        this.cityCount -= 1;
        this.cityIndex.remove(this.cityIndex[index]);
        this.area.cityList.pop();
      }
    },
    addCity(){
      this.cityCount += 1;
      this.cityIndex[this.cityCount - 1] = 1;
      this.area.cityList.push({
          id: null,
          name: '',
          areaId: null,
          addressList: [{
            id: null,
            address: '',
            cityId: null,
            areaId: null,
            createTime: null,
            modifedTime: null,
          }],
          createTime: null,
          modifedTime: null,
      });
    },
    deleteCity2(index){
      this.area.cityList.splice(index, 1);
    },
    addAddress(index){
      this.cityIndex[index] += 1;
      this.area.cityList[index].addressList.push({
        id: null,
        address: '',
        cityId: null,
        areaId: null,
        createTime: null,
        modifedTime: null,
      });
    },
    subtractAddress(index){
      if(this.cityIndex[index] > 1){
        this.cityIndex[index] -= 1;
        this.area.cityList[index].addressList.pop();
      }
    },
    viewArea(){
      if(this.oldArea.id == null){
        alert("请先选择一条数据");
        return;
      }
      this.area.name = JSON.parse(JSON.stringify(this.oldArea.name));
      this.area.cityList = JSON.parse(JSON.stringify(this.oldArea.cityList));
      this.viewflag = true;
      this.addflag = false;
      this.msg = "详情";
      this.isEdit = true;
    },
    editArea(){
      if(this.oldArea.id == null){
        alert("请先选择一条数据");
        return;
      }
      this.area.name = JSON.parse(JSON.stringify(this.oldArea.name));
      this.area.cityList = JSON.parse(JSON.stringify(this.oldArea.cityList));
      this.addflag = false;
      this.msg = "新增/编辑页";
      this.isEdit = true;
    },
    deleteArea(){
      this.$api.deleteArea({
        id: this.oldArea.id,
        name:this.oldArea.name,
        cityList:this.oldArea.cityList,
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
        this.$api.addArea({
          id: null,
          name:this.area.name,
          cityList:this.area.cityList,
          createTime:null,
          modifedTime:null,
        }).then(res=>{
          if(res.status_code===1){
            this.isEdit = false;
            this.$router.go(0);
          }
        })
      }else{
        this.$api.updateArea({
          oldArea: {
            id: this.oldArea.id,
            name:this.oldArea.name,
            cityList:this.oldArea.cityList,
            createTime:null,
            modifedTime:null,
          },
          newArea:
          {
            id: this.area.id,
            name:this.area.name,
            cityList:this.area.cityList,
            createTime:null,
            modifedTime:null,
          }
        }).then(res=>{
          if(res.status_code===1){
            this.isEdit = false;
            this.$router.go(0);
          }
        })
      }
    },
    cancel(){
      if(this.viewflag){
        this.viewflag = false;
      }
      this.addflag = false;
      this.isEdit = false;
      this.cityCount = 0;
    },
    highlightRow(e) {
      if(e.target.parentNode.classList.contains('selected')){
        e.target.parentNode.classList.remove('selected');
        this.oldArea.id = null;
        this.oldArea.name = '';
        this.oldArea.cityList = [];
        this.oldArea.createTime = null;
        this.area = JSON.parse(JSON.stringify(this.oldArea));
      }else{
        let rows = e.target.parentNode.parentNode.getElementsByTagName('tr');
        let index = e.target.parentNode.rowIndex - 1 + (this.currentPage - 1) * this.pageSize;
        for (let i = 0; i < rows.length; i++) {
          rows[i].classList.remove('selected');
        }

        e.target.parentNode.classList.add('selected');
        this.oldArea.id = JSON.parse(JSON.stringify(this.tableData[index].id));
        this.oldArea.name = e.target.parentNode.cells[1].innerText;
        this.oldArea.cityList = JSON.parse(JSON.stringify(this.tableData[index].cityList));
        this.oldArea.createTime = e.target.parentNode.cells[4].innerText;
        this.area = JSON.parse(JSON.stringify(this.oldArea));
      }
    },
    shallowCopy(src) {
      var dst = {};
      for (var prop in src) {
          if (src.hasOwnProperty(prop)) {
              dst[prop] = src[prop];
          }
      }
      return dst;
    }
  }
};
</script>
 
<style scoped>
.container {
  height: 100%;
}
.title {
  border-bottom: 1.5px solid #a4a9acff;
  padding: 10px;
  margin: 10px;
  font-size: 22px;
  font-weight: bold;
  cursor: pointer;
}
.findAreaInput{
  margin-left:0px;
  margin-right: 10px;
  width:150px;
  height:30px;
}
.buttonlist {
  padding: 10px;
  margin: 12px;
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
  display:inline-block;
  width: 100px;
  cursor: pointer;
}
.addButton {
  background-color: rgb(28, 153, 24);
  border-radius: 10px;
  padding: 10px;
  text-align: center;
  display:inline-block;
  cursor: pointer;
}
table {
  width:90%;
  text-align:center;
  border-top: 1px solid #999;
  border-left: 1px solid #999;
  border-spacing: 0;
}
table td {
  padding: 10px 30px;
  border-bottom: 1px solid #999;
  border-right: 1px solid #999;
  white-space: pre-wrap;
}
table th {
  padding: 10px 30px;
  border-bottom: 1px solid #999;
  border-right: 1px solid #999;
}
.cityListDiv{
  background-color: rgb(214, 219, 222);
  margin-left: 35px;
  margin-top: 15px;
  height:220px;
  width: 700px;
}
.select {
  margin-left:0px;
  width:400px;
  height:30px;
}
.selected {
  background-color: #d3d3d3;
}
</style>