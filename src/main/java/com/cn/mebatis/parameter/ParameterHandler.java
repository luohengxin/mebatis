package com.cn.mebatis.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterHandler {

    void handler(PreparedStatement ps, Object[] param);

}
