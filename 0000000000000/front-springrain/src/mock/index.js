import MockAdapter from 'axios-mock-adapter';
import {mockInstance} from '../commons/ajax';
import simplify from './simplify';

const mock = new MockAdapter(mockInstance);

simplify(mock, [
    require('./mock-users').default,
    require('./mock-roles').default,
]);
