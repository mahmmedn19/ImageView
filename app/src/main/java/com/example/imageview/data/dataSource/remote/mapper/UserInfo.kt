package com.example.imageview.data.dataSource.remote.mapper

import com.example.imageview.data.dataSource.remote.models.UserDto
import com.example.imageview.domain.model.UserInfo


fun UserDto.toUserInfo() = UserInfo(
    id = id ?: 0,
    name = name.orEmpty(),
    username = username.orEmpty(),
    email = email.orEmpty(),
    address = UserInfo.Address(
        street = address?.street.orEmpty(),
        suite = address?.suite.orEmpty(),
        city = address?.city.orEmpty(),
        zipcode = address?.zipcode.orEmpty(),
        geo = UserInfo.Address.Geo(
            lat = address?.geo?.lat.orEmpty(),
            lng = address?.geo?.lng.orEmpty()
        )
    ),
    phone = phone.orEmpty(),
    website = website.orEmpty(),
    company = UserInfo.Company(
        name = company?.name.orEmpty(),
        catchPhrase = company?.catchPhrase.orEmpty(),
        bs = company?.bs.orEmpty()
    )
)
