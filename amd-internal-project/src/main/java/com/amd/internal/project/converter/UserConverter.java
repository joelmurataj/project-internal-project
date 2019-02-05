package com.amd.internal.project.converter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.User;

public class UserConverter {

	public static UserDto toJwtUser(User user) {
		if (user != null) {
//			if (user.getSuperior() != null) {
			//List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//			JwtUserDetails userDto = new JwtUserDetails(user.getId(), user.getFirstName(), user.getLastName(),
//					user.getEmail(), user.getPassword(), user.getDepartament().getId(), user.getProject().getId(),
//				user.getSuperior().getId(), user.getRole().getId(), user.getPricePerHour());
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setRoleId(user.getRole().getId());
			userDto.setRoleName(user.getRole().getName());
			String manager= user.getRole().getName();
			//authorities.add(new SimpleGrantedAuthority(manager));
			userDto.getAuthorities().add(new SimpleGrantedAuthority(manager));
			//userDto.setAuthorities(authorities);
			//System.out.println(userDto.getAuthorities().get(0));
			if (user.getSuperior() != null) {
				userDto.setSuperiorId(user.getSuperior().getId());
			}

			return userDto;
		} else {
			return null;
		}

		// }
	}
	
	public static UserDto toUserDto(User user) {
		if (user != null) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setFlag(user.isFlag());
			userDto.setRoleId(user.getRole().getId());
			userDto.setRoleName(user.getRole().getName());
			userDto.setStartDate(user.getStartDate());
			userDto.setDepartamentId(user.getDepartament().getId());
			userDto.setPricePerHour(user.getPricePerHour());
			if(user.getProject() != null) {
			userDto.setProjectId(user.getProject().getId());
			}
			if (user.getSuperior() != null) {
				userDto.setSuperiorId(user.getSuperior().getId());
			}
			return userDto;
		} else {
			return null;
		}

	}
}
