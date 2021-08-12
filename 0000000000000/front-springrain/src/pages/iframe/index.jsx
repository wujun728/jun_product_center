import React, { Component } from 'react';
import config from 'src/commons/config-hoc';
import { PageContent } from 'src/commons/ra-lib';
import './style.less';

@config({
    path: '/iframe_page_/:src',
    keepAlive: true,
})
export default class IFrame extends Component {
    render() {
        let { src } = this.props.match.params;
        src = window.decodeURIComponent(src);
        return (
            <PageContent fitHeight styleName="iframe">
                <iframe
                    allowFullScreen="true"
                    title={src}
                    src={src}
                />
            </PageContent>
        );
    }
}
