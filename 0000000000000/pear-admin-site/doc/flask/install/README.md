### 环境要求 :id=install
- Python >= 3.6
- Mysql >= 5.7.0

###  安装配置
```shell
git clone https://gitee.com/pear-admin/pear-admin-flask.git

# 进 入 项 目 主 目 录

# 创 建 虚 拟 环 境 在 当 前 目 录 的 venv 文 件 夹
python -m venv venv

# 激 活 虚 拟环 境
venv\Scripts\activate

# 安 装
pip install -r requirement.txt

# 配 置 数 据 库
applications\config\database.py

# 初始化数据库
python dev/initDb.py

```



### 设置

```.flaskenv
.flaskenv文件
FLASK_APP=main.py
FLASK_ENV=development
FLASK_DEBUG=1
FLASK_RUN_HOST = 127.0.0.1
FLASK_RUN_PORT = 5000
```



- 如果局域网访问，将FLASK_RUN_HOST设置为 0.0.0.0

  