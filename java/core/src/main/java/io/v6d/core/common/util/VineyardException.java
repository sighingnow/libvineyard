package io.v6d.core.common.util;

public abstract class VineyardException extends Exception {
    public VineyardException(String message) {
        super(message);
    }

    public static class ArrowError extends VineyardException {
        public ArrowError(String message) {
            super(message);
        }
    }

    public static class AssertionFailed extends VineyardException {
        public AssertionFailed(String message) {
            super(message);
        }
    }

    public static class ConnectionError extends VineyardException {
        public ConnectionError(String message) {
            super(message);
        }
    }

    public static class ConnectionFailed extends VineyardException {
        public ConnectionFailed(String message) {
            super(message);
        }
    }

    public static class EndOfFile extends VineyardException {
        public EndOfFile(String message) {
            super(message);
        }
    }

    public static class EtcdError extends VineyardException {
        public EtcdError(String message) {
            super(message);
        }
    }

    public static class GlobalObjectInvalid extends VineyardException {
        public GlobalObjectInvalid(String message) {
            super(message);
        }
    }

    public static class IOError extends VineyardException {
        public IOError(String message) {
            super(message);
        }
    }

    public static class Invalid extends VineyardException {
        public Invalid(String message) {
            super(message);
        }
    }

    public static class InvalidStreamState extends VineyardException {
        public InvalidStreamState(String message) {
            super(message);
        }
    }

    public static class KeyError extends VineyardException {
        public KeyError(String message) {
            super(message);
        }
    }

    public static class MetaTreeInvalid extends VineyardException {
        public MetaTreeInvalid(String message) {
            super(message);
        }
    }

    public static class MetaTreeLinkInvalid extends VineyardException {
        public MetaTreeLinkInvalid(String message) {
            super(message);
        }
    }

    public static class MetaTreeNameInvalid extends VineyardException {
        public MetaTreeNameInvalid(String message) {
            super(message);
        }
    }

    public static class MetaTreeNameNotExists extends VineyardException {
        public MetaTreeNameNotExists(String message) {
            super(message);
        }
    }

    public static class MetaTreeNotInvalid extends VineyardException {
        public MetaTreeNotInvalid(String message) {
            super(message);
        }
    }

    public static class MetaTreeSubtreeNotExists extends VineyardException {
        public MetaTreeSubtreeNotExists(String message) {
            super(message);
        }
    }

    public static class MetaTreeTypeInvalid extends VineyardException {
        public MetaTreeTypeInvalid(String message) {
            super(message);
        }
    }

    public static class MetaTreeTypeNotExists extends VineyardException {
        public MetaTreeTypeNotExists(String message) {
            super(message);
        }
    }

    public static class NotEnoughMemory extends VineyardException {
        public NotEnoughMemory(String message) {
            super(message);
        }
    }

    public static class NotImplemented extends VineyardException {
        public NotImplemented(String message) {
            super(message);
        }
    }

    public static class ObjectExists extends VineyardException {
        public ObjectExists(String message) {
            super(message);
        }
    }

    public static class ObjectIsBlob extends VineyardException {
        public ObjectIsBlob(String message) {
            super(message);
        }
    }

    public static class ObjectNotExists extends VineyardException {
        public ObjectNotExists(String message) {
            super(message);
        }
    }

    public static class ObjectNotSealed extends VineyardException {
        public ObjectNotSealed(String message) {
            super(message);
        }
    }

    public static class ObjectSealed extends VineyardException {
        public ObjectSealed(String message) {
            super(message);
        }
    }

    public static class ServerNotReady extends VineyardException {
        public ServerNotReady(String message) {
            super(message);
        }
    }

    public static class StreamDrained extends VineyardException {
        public StreamDrained(String message) {
            super(message);
        }
    }

    public static class StreamFailed extends VineyardException {
        public StreamFailed(String message) {
            super(message);
        }
    }

    public static class StreamOpened extends VineyardException {
        public StreamOpened(String message) {
            super(message);
        }
    }

    public static class TypError extends VineyardException {
        public TypError(String message) {
            super(message);
        }
    }

    public static class UnknownError extends VineyardException {
        public UnknownError(String message) {
            super(message);
        }
    }

    public static class UserInputError extends VineyardException {
        public UserInputError(String message) {
            super(message);
        }
    }

    public static void check(int code, String message) throws VineyardException {
      switch (code) {
          case 0: return;
          case 1: throw new Invalid(message);
          case 2: throw new KeyError(message);
          case 3: throw new TypError(message);
          case 4: throw new IOError(message);
          case 5: throw new EndOfFile(message);
          case 6: throw new NotImplemented(message);
          case 7: throw new AssertionFailed(message);
          case 8: throw new UserInputError(message);
          case 11: throw new ObjectExists(message);
          case 12: throw new ObjectNotExists(message);
          case 13: throw new ObjectSealed(message);
          case 14: throw new ObjectNotSealed(message);
          case 15: throw new ObjectIsBlob(message);
          case 21: throw new MetaTreeInvalid(message);
          case 22: throw new MetaTreeTypeInvalid(message);
          case 23: throw new MetaTreeTypeNotExists(message);
          case 24: throw new MetaTreeNameInvalid(message);
          case 25: throw new MetaTreeNameNotExists(message);
          case 26: throw new MetaTreeLinkInvalid(message);
          case 27: throw new MetaTreeSubtreeNotExists(message);
          case 31: throw new ServerNotReady(message);
          case 32: throw new ArrowError(message);
          case 33: throw new ConnectionFailed(message);
          case 34: throw new ConnectionError(message);
          case 35: throw new EtcdError(message);
          case 41: throw new NotEnoughMemory(message);
          case 42: throw new StreamDrained(message);
          case 43: throw new StreamFailed(message);
          case 44: throw new InvalidStreamState(message);
          case 45: throw new StreamOpened(message);
          case 51: throw new GlobalObjectInvalid(message);
          default: throw new UnknownError(message);
      }
    }
}
