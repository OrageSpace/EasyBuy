package cn.easybuy.uitls;

public class Constans {
	/**
	 * JSON返回的status状态信息
	 * @author 青云 .ltd
	 *
	 */
	public static interface ReturnResult{
		public static int SUCCESS=1;
		public static int FAIL=-1;
	} 
	
	/**
	  *  用户类型
	 * @author 青云 .ltd
	 *
	 */
	public static interface UserType{
		public static int PRE=0;
		public static int BACKEND=1;
	}
}
