/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.fs.s3a.impl;

import org.apache.hadoop.classification.VisibleForTesting;
import org.apache.hadoop.fs.s3a.impl.logging.LogControl;
import org.apache.hadoop.fs.s3a.impl.logging.LogControllerFactory;

/**
 * This class exists to support workarounds for parts of the AWS SDK
 * which have caused problems.
 */
public final class AwsSdkWorkarounds {

  /**
   * Transfer manager log name. See HADOOP-19272.
   * {@value}.
   */
  public static final String TRANSFER_MANAGER =
      "software.amazon.awssdk.transfer.s3.S3TransferManager";

  private AwsSdkWorkarounds() {
  }

  /**
   * Prepare logging before creating AWS clients.
   * @return true if the log tuning operation took place.
   */
  public static boolean prepareLogging() {
    return LogControllerFactory.createController().
        setLogLevel(TRANSFER_MANAGER, LogControl.LogLevel.ERROR);
  }

  /**
   * Restore all noisy logs to INFO.
   * @return true if the restoration operation took place.
   */
  @VisibleForTesting
  static boolean restoreNoisyLogging() {
    return LogControllerFactory.createController().
        setLogLevel(TRANSFER_MANAGER, LogControl.LogLevel.INFO);
  }
}