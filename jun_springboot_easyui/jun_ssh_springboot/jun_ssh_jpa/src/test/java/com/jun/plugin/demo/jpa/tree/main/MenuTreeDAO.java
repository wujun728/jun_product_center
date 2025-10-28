package com.jun.plugin.demo.jpa.tree.main;

import com.jun.plugin.common.dataaccess.BaseRepository;
import com.jun.plugin.common.tree.enumpath.TreeNodeRepository;

public interface MenuTreeDAO extends TreeNodeRepository<MenuTree, Long>, BaseRepository<MenuTree, Long> {

}
