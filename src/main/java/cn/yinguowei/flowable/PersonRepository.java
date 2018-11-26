package cn.yinguowei.flowable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yin Guo Wei 2018/11/25.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}