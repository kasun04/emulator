package org.wso2.gw.emulator.http.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.wso2.gw.emulator.http.server.contexts.HttpServerInformationContext;

import java.util.concurrent.*;

/**
 * Created by chamile on 12/21/15.
 */
public class HttpChunkedWriteHander extends ChunkedWriteHandler {


    private final HttpServerInformationContext serverInformationContext;
    private final ScheduledExecutorService scheduledWritingExecutorService;
    private final int corePoolSize = 10;

    public HttpChunkedWriteHander(HttpServerInformationContext serverInformationContext) {
        this.serverInformationContext = serverInformationContext;
        scheduledWritingExecutorService = Executors.newScheduledThreadPool(corePoolSize);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        waitingDelay(serverInformationContext.getServerConfigBuilderContext().getWritingDelay());
    }

    private void waitingDelay(int delay) {
        ScheduledFuture scheduledWaitingFuture =
                scheduledWritingExecutorService.schedule(new Callable() {
                    public Object call() throws Exception {
                        return "Writing";
                    }
                }, delay, TimeUnit.MILLISECONDS);
        try {
            System.out.println("result = " + scheduledWaitingFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        scheduledWritingExecutorService.shutdown();
    }
}
