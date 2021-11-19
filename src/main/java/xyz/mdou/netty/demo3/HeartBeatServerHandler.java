package xyz.mdou.netty.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author lizhiyong <lizhiyong@kuaishou.com>
 * Created on 2021-11-19
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            switch (state) {
                case READER_IDLE:
                    System.out.println(ctx.channel().remoteAddress() + " read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println(ctx.channel().remoteAddress() + " write idle");
                    break;
                case ALL_IDLE:
                    System.out.println(ctx.channel().remoteAddress() + " all idle");
            }
        }
        ctx.close();
    }
}
