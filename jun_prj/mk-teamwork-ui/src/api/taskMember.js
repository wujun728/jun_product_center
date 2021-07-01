import $http from '@/assets/js/http'

export function list(data) {
    return $http.post('project/task_member/index', data);
}
export function inviteMemberBatch(data) {
    return $http.post('project/task_member/inviteMemberBatch', data);
}
