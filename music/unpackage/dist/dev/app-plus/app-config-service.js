
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/classify/classify","pages/collect/collect","pages/my/my","pages/details/details"],"window":{"navigationBarBackgroundColor":"#d4d1c8"},"tabBar":{"color":"#d4d1c8","selectedColor":"#e9b0a1","list":[{"iconPath":"static/tabbar/shouye.png","selectedIconPath":"./static/tabbar/shouyeed.png","pagePath":"pages/index/index","text":"首页"},{"iconPath":"static/tabbar/fenlei.png","selectedIconPath":"./static/tabbar/fenleied.png","pagePath":"pages/classify/classify","text":"分类"},{"iconPath":"static/tabbar/like.png","selectedIconPath":"./static/tabbar/liked.png","pagePath":"pages/collect/collect","text":"收藏"},{"iconPath":"static/tabbar/wode.png","selectedIconPath":"./static/tabbar/wodeed.png","pagePath":"pages/my/my","text":"我的"}]},"nvueCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"music","compilerVersion":"2.9.7","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"首页","navigationBarTextStyle":"black"}},{"path":"/pages/classify/classify","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"分类","navigationBarTextStyle":"black"}},{"path":"/pages/collect/collect","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"收藏","navigationBarTextStyle":"black"}},{"path":"/pages/my/my","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我的","navigationBarTextStyle":"black"}},{"path":"/pages/details/details","meta":{},"window":{"navigationBarTitleText":"","enablePullDownRefresh":false}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
