
function httpGet(){

	var http = require('http');
	var url= require('url');
	var querystring = require('querystring');

	/**
	 *===========================================
	 *         自定义模块 start
	 *===========================================
	 */
	var user_login_ui=require('./blocks/user/user_login_ui_1');
	var user_login_action=require('./blocks/user/user_login_action_1');
	var user_regist_ui=require('./blocks/user/user_register_ui');
	var user_find_pass_ui=require('./blocks/user/user_find_password_ui');
	var user_center_ui=require('./blocks/user/user_center_ui');
	var user_code_action=require('./blocks/user/user_code_action');
	var user_info_ui=require('./blocks/user/user_info_ui');
	var user_level_info_ui=require('./blocks/user/user_level_info_ui');
	var user_check_identify_ui=require('./blocks/user/user_check_identify_ui');
	var bottomnv=require('./blocks/test/bottomnv');
	var first_page=require('./blocks/test/first_page');
	var login_page=require('./blocks/test/login_page');
	var home_block=require('./blocks/home_1');
	var welcome_1=require('./blocks/welcome_1');
	var main_ui=require('./blocks/main_1');
	var panel=require('./blocks/panel');

	/**
	 *===========================================
	 *          自定义模块 end
	 *===========================================
	 */
	http.createServer(function (request,response){
		request.setEncoding("utf8");
		response.writeHead(200, {'Content-Type': 'text/html'});
		if(request.method=="POST"){
		   
			var body = '';	
			request.on('data', function (data) {//不停地接收数据
				body += data;
			//	console.log("Partial body: " + body);
			});
			request.on('end', function () {//当数据接收完毕时,会自动回调该方法,所以只要在此进行参数校验,并根据校验情况响应请求
			//	console.log("Body: " + body);
				var objectPostData = querystring.parse(body); 
				var da=JSON.stringify(objectPostData);
				console.log(da);
				var code=objectPostData.code;
				
				if(code=='welcomeUI'){
					welcome_1.welcomeUI(request,response,objectPostData);
				}else if(code=='loginUI'){
					user_login_ui.loginUI(request,response,objectPostData);
				}else if(code=='registerUI'){
					user_regist_ui.register(request,response,objectPostData);
				}else if(code=='home'){
					home_block.loadHome1(request,response,objectPostData);
				}else if(code=='panelUI'){
					panel.panel(request,response,objectPostData);
				}else if(code=='loginAction'){
					user_login_action.loginAction(request,response,objectPostData);
				}else if(code=='main'){
					main_ui.loadMain1(request,response,objectPostData);
				}else if(code=='userCenter'){
					user_center_ui.loadUserCenter(request,response,objectPostData);
				}else if(code=='findPass'){
                 	user_find_pass_ui.loadFindPassword(request,response,objectPostData);
                }else if(code=='codeAction'){
                	user_code_action.codeAction(request,response,objectPostData);
                }else if(code=='identify'){
                	user_check_identify_ui.userIdentify(request,response,objectPostData);
                }else if(code=='info'){
                	user_info_ui.userInfo(request,response,objectPostData);
                }else if(code=='level'){
                	user_level_info_ui.userLevelInfo(request,response,objectPostData);
                }else if(code=='test'){
                	bottomnv.test1(request,response,objectPostData);
                }else if(code=='10002'){
                	login_page.loginUI_1(request,response,objectPostData);
                }else if(code=='10001'){
                      first_page.welcomeUI1(request,response,objectPostData);
                } else{
					response.end('Permission is denied');
				}
				
			});
		}else{
			
			var params=url.parse(request.url,true).query;
			console.log(params);
			var code=params.code;
			if(code=='welcomeUI'){
				welcome_1.welcomeUI(request,response,params);
			}else if(code==null){
				var string =JSON.stringify(exports.loginUIJson);
					response.end(string);
			}else if(code=='loginAction'){
				user_login_action.loginAction(request,response,params);
			}else if(code=='panelUI'){
				panel.panel(request,response,params);
			}else if(code=='home'){
					home_block.loadHome1(request,response,params);
			}else if(code=='loginUI'){
				user_login_ui.loginUI(request,response,params);
			}else if(code=='registerUI'){
				user_regist_ui.register(request,response,params);
			}else if(code=='main'){
				main_ui.loadMain1(request,response,params);
			}else if(code=='findPass'){
                user_find_pass_ui.loadFindPassword(request,response,params);
            }else if(code=='userCenter'){
             	user_center_ui.loadUserCenter(request,response,params);
            }else if(code=='test'){
                bottomnv.test1(request,response,params);
            }else if(code=='identify'){
                user_check_identify_ui.userIdentify(request,response,params);
            }else if(code=='level'){
                user_level_info_ui.userLevelInfo(request,response,params);
            }else if(code=='info'){
                user_info_ui.userInfo(request,response,params);
            }else if(code=='codeAction'){
                user_code_action.codeAction(request,response,params);
            }else if(code=='10002'){
				login_page.loginUI_1(request,response,params);
			 }
            else if(code=='10001'){
            	first_page.welcomeUI1(request,response,params);
            }
		}
		
	}).listen(8083);
}
exports.httpGet=httpGet;
httpGet();


