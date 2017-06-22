//请不要修改本文件名
package serviceImpl.executePart;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import service.ExecuteService;
import service.UserService;
import serviceImpl.executePart.commends.Operator;
import serviceImpl.executePart.languageCollector.languageInterface.LanguageTranslator;
import serviceImpl.executePart.languageCollector.languages.BFTranslator;
import serviceImpl.executePart.languageCollector.languages.Language;
import serviceImpl.executePart.languageCollector.languages.OokTranslator;

public class ExecuteServiceImpl implements ExecuteService {
	private Map<String,Operator> map=new HashMap<>();
	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		Operator operator=new Operator(code,param);
		operator.execute();
		return operator.getResult();
	}

	@Override
	public String execute(String code, String param, String language, String userId) throws RemoteException {
		code=Language.translate(code,language);
		Operator operator=new Operator(code,param);
		operator.execute();
		map.remove(userId);
		map.put(userId,operator);
		return operator.getResult();
	}
}
