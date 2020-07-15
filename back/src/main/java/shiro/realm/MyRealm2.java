package shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

    /**
     * 返回一个唯一的Realm名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "myrealm1";
    }

    /**
     * 判断此Realm是否支持此Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 根据Token获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码
        if (!"wang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}