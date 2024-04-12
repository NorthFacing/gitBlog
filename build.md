
参考 `deploy.sh`:
```shell
# 添加依赖组件
bundle add jekyll
# 运行
bundle exec jekyll serve --config _config.yml
# 编译
bundle exec jekyll build --config _config.yml,_config_api.yml
bundle exec jekyll build --config _config.yml,_config_home.yml
bundle exec jekyll build --config _config.yml,_config_speed.yml
```



