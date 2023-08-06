package com.circle.permission;

/**
 * Created by zhy on 16/2/21.
 */
public interface PermissionProxy<T>
{
    void grant(T source, int requestCode);

    void denied(T source, int requestCode);

    void rationale(T source, int requestCode);

    /**
     * 理解为是否需要检测requestCode对应的权限组是否是处【之前又被临时性拒绝】
     * 如果你设置了@ShowRequestPermissionRationale注解的话,就是需要的,返回true,如果真被检测出来之前有被拒绝过,就会走上面rationale方法
     * 否则返回false
     * @param requestCode 该请求码对应的权限
     * @return false:不需要检测 true:需要检测
     */
    boolean needShowRationale(int requestCode);
}
