package com.ty.ibest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ty.ibest.entity.SubCmOrder;
public interface SubCmOrderMapper {
	Integer addSubCmOrders(@Param("subCmOrders") List<SubCmOrder> subCmOrders);

}
