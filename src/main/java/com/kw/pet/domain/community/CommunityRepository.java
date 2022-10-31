//package com.kw.pet.domain.community;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import com.kw.pet.config.BaseException;
//
//import java.util.List;
//
//public interface CommunityRepository extends JpaRepository<Community, Long> {
//
//    Community findById(int postId) ;
//
//
//    //post_id에 맞게 내용뽑아내기
//    @Query(value = "select * from post where post_id = ?;" , nativeQuery = true)
//    List<Community> findByPostId(int treeId) throws BaseException;
//
//}
