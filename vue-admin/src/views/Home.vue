<template>
  <div class="home" style="width: 100%; height: 565px;">
	  <div class="position-absolute rounded-circle" style="width: 40%; height: 340px;top: 130px;left: 180px;">
		  <div class="flex-1">
			  <img src="../assets/login.png" class="rounded w-100" style="height: 100%;" model="heightFit" v-show="!isFaceLogin">
			  <video id="video" width="630px" height="370px" autoplay="autoplay" v-show="isFaceLogin"></video>
			  <canvas id="canvas" width="630px" height="370px" v-show="isFaceLogin"></canvas>
		  </div>
	  </div>
	  <div class="position-absolute pt-2 " style="width: 300px; height: 340px;top: 110px;right: 210px;">
		  <h3 class="text-white">管理员登录</h3>
		  <div class="w-100 border-bottom flex" style="justify-content: space-around;">
			  <p class="text-white">请使用账号密码或人脸识别</p>
			  <img src="../assets/ewm.png" style="width: 40px;height: 40px;">
		  </div>
		  <br>
		  <div class="w-100 ml-3" style="height: 30px;">
			   <input type="text" v-model="username" placeholder="USERNAME" class="rounded" style="border: none; width: 250px;height:100%;">
		  </div>
		  <br>
		  <div class="w-100 ml-3" style="height: 30px;">
		  		<input type="password" v-model="password" :disabled="isFaceLogin == true" placeholder="PASSWORD" class="rounded" style="border: none; width: 250px;height:100%;">
		  </div>
		  <br>
		  <div class="w-100 flex" style="justify-content: space-around;">
			  <div class="flex ml-3">
			  	 <input type="checkbox" name="form-face" style="zoom: 130%;" v-model="isFaceLogin" @change="useFaceLogin()">
			  	  <p class="text-white" style="font-size:10px; margin-top: 1px;">人脸识别登录</p>
			  </div>
			  <p class="text-white" style="font-size: 10px;margin-top: 1px;">建议在Chrome下使用本系统</p>
		  </div>
		  <br>
			<button @click="login()" class="card border text-white ml-3" style="font-size:10px;border: none;background-color: #72cdb7;">戳我登录{{message}}</button>
	  </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
	  return {
		  message: 'Hello Vue!',
		  isFaceLogin: false, // 是否开启人脸登录
		  mediaStreamTrack: null, // 全局视频对象
		  video: null,
		  isCameraOpen: false,// 摄像头是否打卡
		  username: '',
		  password: '',
	  }
  },
  created() {
  	
  },
  methods:{
	  login() {
		  let username = this.username
		  if(username == null || username == undefined || username == '') {
			  alert('用户名不能为空')
			  return
		  }
		  // 使用人脸登录
		  if(this.isFaceLogin) {
			  let video = document.getElementById('video')
			  let canvas = document.getElementById('canvas')
			  let ctx = canvas.getContext('2d')
			  ctx.drawImage(video , 0 , 0 , 630 , 370)
			  let img = document.getElementById('canvas').toDataURL()
			  // 截取base64 图片
			  console.log(img)
			  let img64 = img.split('base64,')[1]
			  console.log(img64);
		  }else {
			  let password = this.password
			  if(password == null || password == undefined || password == '') {
				  alert('密码不能为空')
				  return
			  }
			  let adminUserLogin = {}
			  adminUserLogin.username = username
			  adminUserLogin.password = password
			  // 用户名密码登录
			 this.axios.post('http://localhost:8005/adminMsg/adminLogin',adminUserLogin
			 ).then((res) => {
				 console.log(res)
				 if(res.data.status == 200) {
					 alert('登录成功')
					 this.username = ''
					 this.password = ''
				 } else{
					 alert(res.data.msg)
				 }
			 })
		  }
	  },
	  useFaceLogin() {
		  // 如果true 打开摄像头
		  if(this.isFaceLogin) {
			  this.openFace()
		  }
	  },
	  openFace() {
		  // 如果摄像头已经打开了，就不再继续下方代码
		  if(this.isCameraOpen) {
			  return
		  }
		  let constraints = {
			  video: {width:630,height:370},
			  audio: false
		  }
		  // 获得video摄像头
		  let  video = document.getElementById('video')
		  let promise = navigator.mediaDevices.getUserMedia(constraints)
		  promise.then((mediaStream) => {
			let  mediaStreamTrack = mediaStream.getVideoTracks()
			console.log(mediaStreamTrack+"11111");
			  video.srcObject = mediaStream
			  video.play()
		  })
		  video.onloadedmetadata = function() {
			  this.isCameraOpen = true
		  }
	  },
	  closeFace() {
		  let  video = document.getElementById('video')
		  let stream = video.srcObject
		  if(stream == null) {
			  return
		  }
		  let tracks = stream.getTracks()
		  tracks.forEach(function (track) {
			  track.stop()
		  })
		  video.srcObject = null
	  },
  },
};
</script>
<style>
@import url("../common/css/free.css");
.home{
	background-image: url(../assets/bj.jpg);
	position: relative;
	z-index: 100;
	opacity: 0.8;
	}
	.card {
		height: 30px;
		width: 250px;
	}
</style>
