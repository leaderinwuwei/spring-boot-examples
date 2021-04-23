package com.neo.demo.service.impl;

import com.neo.demo.entity.TableNameDemo;
import com.neo.demo.mapper.TableNameDemoMapper;
import com.neo.demo.service.ITableNameDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试CPU暴涨 服务实现类
 * </p>
 *
 * @author monta
 * @since 2021-04-22
 */
@Service
public class TableNameDemoServiceImpl extends ServiceImpl<TableNameDemoMapper, TableNameDemo> implements ITableNameDemoService {

}
