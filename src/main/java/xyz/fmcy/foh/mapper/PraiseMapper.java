package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.fmcy.foh.pojo.Praise;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/22 23:33
 */
@Mapper
public interface PraiseMapper {
    int addPraise(Praise praise);

    int topicPraiseNumber(Integer id);

    int deletePraise(Praise praise);

    List<Praise> topicPraises(Integer id);

    int lovedIt(Praise praise);
}
