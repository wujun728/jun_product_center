package webservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sz.message.request.UserPresenceRequestMessage;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.vo.JameMessage;

@Service
public interface RegUserServiceClient {

	public boolean regUserToServer(UserRegRequestMessage userRegRequestMessage);

	public boolean updUserPwdToServer(
			UserRegRequestMessage userRegRequestMessage);

	public boolean loginUserToServer(UserRegRequestMessage userRegRequestMessage);
	
	public List<JameMessage> getChatMessage(UserPresenceRequestMessage userPresenceRequestMessage);

}
