/// <reference path="./image-encode-options.d.ts" />

interface OffscreenCanvasRenderingContext2D extends CanvasRenderingContext2D {}

interface OffscreenCanvas extends EventTarget {
  width: number;
  height: number;
  getContext(
    contextId: "2d",
    contextAttributes?: CanvasRenderingContext2DSettings
  ): OffscreenCanvasRenderingContext2D | null;
  transferToImageBitmap(): ImageBitmap;
  convertToBlob(options?: ImageEncodeOptions): Promise<Blob>;
}

interface HTMLCanvasElement {
  transferControlToOffscreen(): OffscreenCanvas;
}
