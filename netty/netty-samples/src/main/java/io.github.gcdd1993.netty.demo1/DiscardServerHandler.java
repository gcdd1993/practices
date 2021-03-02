package io.github.gcdd1993.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 *
 * @author gcdd1993
 * @date 2021/2/27
 * @since 1.0.0
 */
public class DiscardServerHandler
        extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // Do something with msg
            ByteBuf in = (ByteBuf) msg;
            try {
                while (in.isReadable()) { // (1)
                    System.out.print((char) in.readByte());
                    System.out.flush();
                }
            } finally {
                // ByteBuf是一个引用计数的对象，必须通过release()方法显式释放。
                ReferenceCountUtil.release(msg); // (2)
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
