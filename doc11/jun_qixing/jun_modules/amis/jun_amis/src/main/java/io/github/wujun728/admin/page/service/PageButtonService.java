package io.github.wujun728.admin.page.service;


import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.data.*;

import java.util.List;
import java.util.Map;

public interface PageButtonService {
    Map<String, Object> getButton(BaseButton baseButton);

    List<PageButton> byPageCode(String pageCode);

    void save(PageButton pageButton);

    List<PageButton> byPageId(Long id);

    List<PageButton> byPage(Page page);

    List<PageButton> getByForm(Form form);

    Page getPage(PageButton pageButton);

    PageButtonData dealPageButton(List<PageButton> pageButtons, boolean isRow);
}
