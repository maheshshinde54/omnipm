package com.beatrix.omniPM.user.respositories;

import com.beatrix.omniPM.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
}
