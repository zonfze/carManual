
function codeAction(req,res,DATA){
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
		msg:'验证码发送完成'
	};
	var phone=DATA.phone;
	if(phone=='18319388532'){
		res.end(JSON.stringify(json_action));
	}
}
exports.codeAction=codeAction;