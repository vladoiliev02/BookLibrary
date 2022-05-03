package com.fmi.wdj.booklibrary.repository.user;

import com.fmi.wdj.booklibrary.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserInfo, Long> {

}
