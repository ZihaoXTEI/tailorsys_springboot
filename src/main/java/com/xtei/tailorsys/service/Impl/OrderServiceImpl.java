package com.xtei.tailorsys.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.*;
import com.xtei.tailorsys.mapper.VO.OrderViewVOMapper;
import com.xtei.tailorsys.entity.Order;
import com.xtei.tailorsys.entity.OrderFabricDetail;
import com.xtei.tailorsys.entity.OrderProcess;
import com.xtei.tailorsys.entity.VO.OrderFabricDetailVO;
import com.xtei.tailorsys.entity.VO.OrderViewVO;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * FileName: OrderServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/13 21:05
 * Description:
 */

@Service("orderService")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderFabricDetailMapper orderFabricDetailMapper;
    @Resource
    private OrderProcessMapper orderProcessMapper;
    @Resource
    private OrderViewVOMapper orderViewVOMapper;
    @Resource
    private FabricStockInfoMapper fabricStockInfoMapper;


    /**
     * 获取订单列表
     */
    @Override
    public PageResult findOrderViewList(String orderId,
                                        String customerName,
                                        Integer clothTypeId,
                                        Integer paymentMethod,
                                        Integer orderStatus,
                                        String screenField,
                                        Date[] date,
                                        String orderType,
                                        Integer pageNum,
                                        Integer pageSize) {
        return PageHelperUtils.getPageResult(getPageInfo(orderId, customerName, clothTypeId, paymentMethod, orderStatus, screenField, date, orderType, pageNum, pageSize));
    }

    private PageInfo<OrderViewVO> getPageInfo(String orderId,
                                              String customerName,
                                              Integer clothTypeId,
                                              Integer paymentMethod,
                                              Integer orderStatus,
                                              String screenField,
                                              Date[] date,
                                              String orderType,
                                              Integer pageNum,
                                              Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Date[] dateT = date;
        if (dateT == null || dateT.length != 2) {
            dateT = new Date[2];
        }

        List<OrderViewVO> orderViewVOList = orderViewVOMapper.selectAll(orderId, customerName, clothTypeId, paymentMethod, orderStatus, screenField, dateT[0], dateT[1], orderType);
        return new PageInfo<OrderViewVO>(orderViewVOList);
    }

    /**
     * 根据订单编号获取订单信息
     */
    @Override
    public Order findOrderById(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        return order;
    }

    /**
     * 根据订单编号获取订单详细信息
     */
    @Override
    public List<OrderFabricDetailVO> findOrderDetailAndFabricInfoList(String orderId) {
        List<OrderFabricDetailVO> orderFabricDetailVOList = orderFabricDetailMapper.selectOrderDetailAndFabricInfo(orderId);
        return orderFabricDetailVOList;
    }

    /**
     * 提交订单
     */
    @Override
    public boolean submitOrder(Order order, List<OrderFabricDetail> orderFabricDetails) {
        String orderId = order.getOrderId();
        OrderProcess orderProcess = new OrderProcess();
        orderProcess.setOrderId(orderId);
        boolean status = false;
        orderMapper.insert(order);
        orderProcessMapper.insert(orderProcess);

        Iterator<OrderFabricDetail> iterator = orderFabricDetails.iterator();

        while (iterator.hasNext()) {
            OrderFabricDetail orderFabricDetail = iterator.next();
            orderFabricDetail.setOrderId(orderId);
            orderFabricDetail.setOfdUsage(0.0);
            orderFabricDetailMapper.insert(orderFabricDetail);
        }

        status = true;
        return status;

    }

    @Override
    public int addOrder(Order order) {
        int res = orderMapper.insert(order);
        return res;
    }

    @Override
    public int updateOrder(Order order) {
        int res = orderMapper.updateByPrimaryKey(order);
        return res;
    }

    @Override
    public int addOrderFabricDetail(OrderFabricDetail orderFabricDetail) {
        orderFabricDetail.setOfdUsage(0.0);
        int res1 = orderFabricDetailMapper.insert(orderFabricDetail);
        //更新库存预定量
        double fasPrede = fabricStockInfoMapper.getFasPredeByFabricId(orderFabricDetail.getFabricId());
        fasPrede += orderFabricDetail.getOfdPrede();
        int res2 = fabricStockInfoMapper.updateStockByFabricId(orderFabricDetail.getFabricId(), 0.0, fasPrede, 0.0);

        return res1 + res2;
    }

    @Override
    public int updateOrderFabricDetail(OrderFabricDetail orderFabricDetail) {
        OrderFabricDetail old_orderFabricDetail = orderFabricDetailMapper.selectByPrimaryKey(orderFabricDetail.getOfdNo());

        //更新库存预定量
        double fasPrede = fabricStockInfoMapper.getFasPredeByFabricId(orderFabricDetail.getFabricId());
        double fasStock = fabricStockInfoMapper.getFasStockByFabricId(orderFabricDetail.getFabricId());

        fasPrede = (fasPrede - old_orderFabricDetail.getOfdPrede()) + orderFabricDetail.getOfdPrede();
        fasStock = (fasStock + old_orderFabricDetail.getOfdUsage()) - orderFabricDetail.getOfdUsage();

        int res1 = fabricStockInfoMapper.updateStockByFabricId(orderFabricDetail.getFabricId(), fasStock, fasPrede, 0.0);
        int res2 = orderFabricDetailMapper.updateByPrimaryKey(orderFabricDetail);

        return res1 + res2;
    }

    @Override
    public int deleteOrderFabricDetail(Integer ofdNo) {
        OrderFabricDetail old_orderFabricDetail = orderFabricDetailMapper.selectByPrimaryKey(ofdNo);

        double fasPrede = fabricStockInfoMapper.getFasPredeByFabricId(old_orderFabricDetail.getFabricId());
        double fasStock = fabricStockInfoMapper.getFasStockByFabricId(old_orderFabricDetail.getFabricId());

        fasPrede = fasPrede - old_orderFabricDetail.getOfdPrede();
        fasStock = fasStock + old_orderFabricDetail.getOfdUsage();

        int res1 = fabricStockInfoMapper.updateStockByFabricId(old_orderFabricDetail.getFabricId(), fasStock, fasPrede, 0.0);
        int res2 = orderFabricDetailMapper.deleteByPrimaryKey(ofdNo);

        return res1 + res2;

    }

    @Override
    public int updateOrderStatus(String orderId,String userName,Date dateTime,Integer orderStatus){
        OrderProcess orderProcess = orderProcessMapper.selectByOrderId(orderId);
        if(orderStatus == 2){
            orderProcess.setTailoring(userName);
            orderProcess.setTailoringDate(dateTime);
        }else if(orderStatus == 3){
            orderProcess.setSewing(userName);
            orderProcess.setSewingDate(dateTime);
        }else if(orderStatus == 4){
            orderProcess.setIroning(userName);
            orderProcess.setIroningDate(dateTime);
        }else if(orderStatus == 5){
            orderProcess.setFinish(userName);
            orderProcess.setFinishDate(dateTime);
        }

        int res1 = orderProcessMapper.updateByOrderId(orderProcess);
        int res2 = orderMapper.updateOrderStatus(orderId,orderStatus);
        return res1 +res2;
    }

    @Override
    public int getNumberOfOrder(){
        int res = orderMapper.getNumberOfOrder();
        return res;
    }

    @Override
    public int getNumberOfIncompleteOrder(){
        int res = orderMapper.getNumberOfIncompleteOrder();
        return res;
    }

    @Override
    public List<Map> getInfoOfIncompleteOrder() {
        List<Map> dataList = orderMapper.getInfoOfIncompleteOrder();
        return dataList;
    }

    @Override
    public List<OrderViewVO> getAllOrder(){
        List<OrderViewVO> orderViewVOList = orderViewVOMapper.selectAll(null,null,null,null,null,null,null,null,null);
        return orderViewVOList;
    }

    @Override
    public List<OrderProcess> getAllOrderProcess(){
        List<OrderProcess> orderProcessList = orderProcessMapper.selectAll(null);
        return orderProcessList;
    }

    @Override
    public List<Order> getAllOrderList() {
        List<Order> orderList = orderMapper.selectAll();
        return orderList;
    }

}
