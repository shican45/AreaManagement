<template>
  <div id="container">
    <div id="header">
      <router-link to="/" class="head">区域管理功能</router-link>
    </div>

    <div id="menu" >
      <div class="item" tabindex="1" @click="toArea()">域区管理</div>
      <div class="item" tabindex="1" @click="toCity()">城市信息</div>
    </div>

    <div id="content" style="">
      <router-view/></div>
  </div>
</template>

<script>
export default {
  name: 'App',
  created(){
  },
  methods: {
    toArea() {
      if ('/area' !== this.$route.path) {
        this.$router.push({path: '/area'});
      }
    },
    toCity() {
      if ('/city' !== this.$route.path) {
        this.$router.push({path: '/city'});
      }
    },
  }
}
</script>

<style>
#container {
  position:fixed;
  overflow: auto;
  height:100%;
  width:100%;
  top:0;
  left:0;
}
#header {
  background-color: #263238;
  margin-left:0px;
  margin-top:5px;
  margin-bottom:2px;
  padding-top: 10px;
  padding-bottom: 10px;
  font-size: 40px;
  color: white;
}
.head {
  background-color: #263238;
  text-decoration: none;
  margin-left:20px;
  margin-top:5px;
  font-size: 40px;
  color: white;
}
#menu {
  background-color:#263238;
  height:100%;
  width:12%;
  float:left;
  padding-top: 30px;
  font-size:20px;
  color: white;
  text-align: center;
}
.item {
  border-bottom: 1.5px solid #455A64;
  padding: 10px;
  margin: 10px;
  cursor: pointer;
}
.item:hover {
  background-color: #455A64;
}
#content {
  margin-left:0px;
  margin-top:0px;
  padding-top: 10px;
  padding-bottom: 10px;
  background-color:#EEEEEE;
  height:100%;
  width:88%;
  float:left;
}

.nav a{
  background: none;
  border-bottom: 1px solid #455A64;
  color: #CFD8DC;
  font-size: 14px;
}
</style>
