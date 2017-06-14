package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
