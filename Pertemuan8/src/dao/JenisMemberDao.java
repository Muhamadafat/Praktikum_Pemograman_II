package Pertemuan8.src.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Pertemuan7.Membership.src.model.JenisMember;

public class JenisMemberDao {

    private final SqlSessionFactory sqlSessionFactory;

    public JenisMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.insert("mapper.JenisMemberMapper.insert", jenisMember);
            session.commit();
        }

        return result;
    }

    public int update(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.update("mapper.JenisMemberMapper.update", jenisMember);
            session.commit();
        }
        return result;
    }

    public int delete(String jenisMemberId) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("mapper.JenisMemberMapper.delete", jenisMemberId);
            session.commit();
        }
        return result;
    }

    public List<JenisMember> findAll() {
        List<JenisMember> jenisMembers;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            jenisMembers = session.selectList("mapper.JenisMemberMapper.findAll");
        }

        return jenisMembers;
    }

}