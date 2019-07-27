package cn.easybuy.uitls;

/**
 * Ajax请求信息返回类
 * @author 青云 .ltd
 *
 */
public class ReturnResult {
	private int status;//请求状态
	private Object data;//返回请求的数据 如JSON
	private String message="请求成功！";//返回客户端的消息
	
	//构造方法
	public ReturnResult() {}
	
	public ReturnResult(int status,Object data,String message) {
		this.status=status;
		this.data=data;
		this.message=message;
	}
	
	public ReturnResult(Object data) {
		this.status=Constans.ReturnResult.SUCCESS;
		this.message=message;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 默认返回成功状态信息
	 * @return 结果对象
	 */
	public Object returnSuccess() {
		this.status=Constans.ReturnResult.SUCCESS;
		return this;
	}
	
	/**
	 * 返回成功信息
	 * @param data
	 * @param message
	 * @return 结果对象
	 */
	public Object returnSuccess(Object data,String message) {
		this.status=Constans.ReturnResult.SUCCESS;
		this.data=data;
		this.message=message;
		return this;
	}
	
	/**
	  * 返回请求失败信息
	 * @param message
	 * @return
	 */
	public Object returnFail(String message) {
		this.message=message;
		this.status=Constans.ReturnResult.FAIL;
		return this;
	}
}
