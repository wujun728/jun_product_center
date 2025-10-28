package io.github.wujun728.admin.page.service;

import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.data.PageButton;

import java.util.List;

/**
 * io.github.wujun728.admin.page.service.impl
 *
 * @author Leo Liu
 * @created 2022/4/8 2:36 PM
 */
public interface PageButtonDao {

    void save(PageButton pageButton);

    List<PageButton> byPageId(Long id);

    List<PageButton> byPage(Page page);

    List<PageButton> getByForm(Form form);

    void del(PageButton pageButton);
}
