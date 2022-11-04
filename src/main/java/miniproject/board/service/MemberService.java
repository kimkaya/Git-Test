package miniproject.board.service;


import miniproject.board.domain.Member;
import miniproject.board.repository.JpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final JpaMemberRepository repository ;

    @Autowired
    public MemberService(JpaMemberRepository repository){
        this.repository = repository;
    }
    /**
     * 회원 가입
     */
    public Long memberJoin(Member member){
        return repository.save(member).getId();
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findAllMembers(){
        return repository.findAll();
    }

    /**
     * 한건의 회원정보를 수정하거나 삭제하기 위해 가져온다.
     * @param id
     * @return
     */
    public Optional<Member> findMember(Long id){
        return repository.findById(id);
    }
    /**
     * 한건의 회원정보를 삭제한다.
     * @param id
     * @return
     */
    public void removeMember(Long id) {

        repository.deleteById(id);
    }
    /**
     * 한건의 회원정보를 수정한다.
     * @param member
     * @return
     */
    public Long updateMember(Member member){
        return repository.save(member).getId();
    }
}