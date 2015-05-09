package demo.ext;

import java.util.Optional;

import javax.annotation.Nullable;

/**
 * 运行模式.
 * 
 * @author itang
 *
 */
public enum RunMode {
    // 开发模式
    Dev,
    // 单元测试模式
    Unittest,
    // 生产模式(部署模式|集成测试模式)
    Prod;

    public boolean isDevMode() {
        return this == Dev;
    }

    public boolean isProdMode() {
        return this == Prod;
    }

    public boolean isTestunitMode() {
        return this == Unittest;
    }

    public static final String RUN_MODE_KEY = "run.mode";
    public static final String RUN_MODE_DEV = "dev";
    public static final String RUN_MODE_PROD = "prod";
    public static final String RUN_MODE_TEST = "unittest";

    public static Optional<RunMode> fromString(@Nullable final String v) {
        if (v == null) {
            return Optional.empty();
        }
        switch (v.toLowerCase()) {
        case RUN_MODE_DEV:
            return Optional.of(RunMode.Dev);
        case RUN_MODE_PROD:
            return Optional.of(RunMode.Prod);
        case RUN_MODE_TEST:
            return Optional.of(RunMode.Unittest);
        default:
            throw new RuntimeException("Unkown Run Mode:" + v);
        }
    }
}
