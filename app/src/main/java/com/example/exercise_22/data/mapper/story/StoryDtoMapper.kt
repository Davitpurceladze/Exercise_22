package com.example.exercise_22.data.mapper.story

import com.example.exercise_22.data.model.story.StoryDto
import com.example.exercise_22.domain.model.story.GetStory

fun StoryDto.toDomain() = GetStory(cover = cover, id = id, title = title)