package com.rentFriend.rentFriend.dtos;

import java.util.UUID;

import com.rentFriend.rentFriend.models.PostModel;
import com.rentFriend.rentFriend.models.UserModel;

public record CommentsDto(UserModel user, PostModel post, String comments, String created_at, String update_at, UUID idPost) {

}
