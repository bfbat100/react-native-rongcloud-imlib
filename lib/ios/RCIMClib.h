#import <React/RCTEventEmitter.h>
#import <RongIMLib/RongIMLib.h>
#import "RCCustomMessageContent.h"

@interface RCIMClib : RCTEventEmitter <RCTBridgeModule, RCConnectionStatusChangeDelegate,
                                       RCIMClientReceiveMessageDelegate, RCTypingStatusDelegate, RCLogInfoDelegate>
@end
