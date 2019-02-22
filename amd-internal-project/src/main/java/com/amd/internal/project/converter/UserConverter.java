package com.amd.internal.project.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.entity.Departament;
import com.amd.internal.project.entity.Rank;
import com.amd.internal.project.entity.Role;
import com.amd.internal.project.entity.User;

public class UserConverter {

	public static UserDto toJwtUser(User user) {
		if (user != null) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setRoleId(user.getRole().getId());
			userDto.setRoleName(user.getRole().getName());
			userDto.setDepartamentId(user.getDepartament().getId());
			userDto.setDepartamentName(user.getDepartament().getName());
			userDto.setStartDate(user.getStartDate());
			String role = user.getRole().getName();
			userDto.getAuthorities().add(new SimpleGrantedAuthority(role));
			userDto.setRankId(user.getRank().getIdRank());
			userDto.setRankName(user.getRank().getName());
			if (user.getSuperior() != null) {
				userDto.setSuperiorId(user.getSuperior().getId());
			}
			return userDto;
		} else {
			return null;
		}
	}

	public static UserDto toUserDto(User user) {
		if (user != null) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setFlag(user.isActivated());
			userDto.setRoleId(user.getRole().getId());
			userDto.setRoleName(user.getRole().getName());
			userDto.setStartDate(user.getStartDate());
			userDto.setDepartamentId(user.getDepartament().getId());
			userDto.setDepartamentName(user.getDepartament().getName());
			userDto.setPricePerHour(user.getPricePerHour());
			userDto.setRankId(user.getRank().getIdRank());
			userDto.setRankName(user.getRank().getName());
			if (!user.getProjects().isEmpty()) {
				userDto.setProjects(user.getProjects());
			}
			if (user.getSuperior() != null) {
				userDto.setSuperiorId(user.getSuperior().getId());
			}
			return userDto;
		} else {
			return null;
		}

	}
	
	public static User toUser(UserDto userDto) {
		if (userDto != null) {
			User user = new User();
			user.setActivated(true);
			Departament departament = new Departament();
			departament.setId(userDto.getDepartamentId());
			user.setDepartament(departament);
			user.setEmail(userDto.getEmail());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setPassword(userDto.getPassword());
			Rank rank = new Rank();
			rank.setIdRank(userDto.getRankId());
			user.setRank(rank);
			Role role = new Role();
			role.setId(userDto.getRoleId());
			user.setRole(role);
//			User superior = new User();
//			user.setId(userDto.getSuperiorId());
//			user.setSuperior(superior);
			user.setStartDate(userDto.getStartDate());
			user.setPricePerHour(userDto.getPricePerHour());
			return user;
		} else {
			return null;
		}

	}

//	public static Set<UserDto> toUserSetDto(Set<User> list) {
//		Set<UserDto> userDto = new HashSet<UserDto>();
//		if (list != null) {
//			for (User user : list) {
//				userDto.add(toUserDto(user));
//			}
//			return userDto;
//		} else {
//			return userDto;
//		}
//	}
	
	public static List<UserDto> toUserListDto(List<User> list){
		ArrayList<UserDto> userDto = new ArrayList<UserDto>();
		if (list != null) {
			for (User user : list) {
				userDto.add(toUserDto(user));
			}
			return userDto;
		} else {
			return userDto;
		}
	}
}
