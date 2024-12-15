package mvc.src;

import org.apache.ibatis.session.SqlSession;

import mvc.src.controller.UserController;
import mvc.src.model.MyBatisUtil;
import mvc.src.model.UserMapper;
import mvc.src.view.UserView;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        UserView view = new UserView();
        new UserController(view, mapper);

        view.setVisible(true);
    }
}