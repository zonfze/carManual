
function loginAction(req,res,DATA){
	var json_action={
		retCode:101,
		data:{
			res_type:'1002',
			eventLink:[{
				event_type:'1000',
				actionLink:[{
					actionType:'10009',
					url:'http://192.168.16.198:8083/',
					params:[
						'device_id=12345678',
						'code=main'
					],
					modify:[]
				}]
			}]
		},
		msg:'登录成功'
	};
	var username=DATA.username;
	var password=DATA.userpass;
	if(username=='test'&&password=='123456'){
		res.end(JSON.stringify(json_action));
	}else{
		res.end(JSON.stringify({retCode:100,msg:'登录失败'}));
	}
}
exports.loginAction=loginAction;