package com.kw.pet.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeResponseDto {

    @ApiModelProperty(value = "좋아요 상태")
    private boolean likeStatus;

}