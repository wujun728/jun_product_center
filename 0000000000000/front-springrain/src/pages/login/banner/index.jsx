import React, { Component } from 'react';
import './style.less';
import cfg from 'src/config';

const { appName } = cfg;

export default class index extends Component {
  state = {
    isMount: false,
  };

  componentDidMount() {
    this.setState({ isMount: true });
  }

  render() {
    const { isMount } = this.state;

    return (
      <div styleName={isMount ? 'root active' : 'root'}>
        <div styleName="logo">
          <span>{appName}</span>
        </div>
      </div>
    );
  }
}
