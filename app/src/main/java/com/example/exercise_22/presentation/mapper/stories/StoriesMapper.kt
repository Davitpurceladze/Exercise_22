package com.example.exercise_22.presentation.mapper.stories

import com.example.exercise_22.domain.model.story.GetStory
import com.example.exercise_22.presentation.model.story.Story

fun GetStory.toPresenter() = Story(cover = cover, id = id, title = title)