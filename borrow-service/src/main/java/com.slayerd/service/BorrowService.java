package com.slayerd.service;

import com.slayerd.entity.UserBorrowDetail;

public interface BorrowService {

    UserBorrowDetail getUserBorrowDetailByUid(int uid);

    boolean doBorrow(int bid , int uid);
}